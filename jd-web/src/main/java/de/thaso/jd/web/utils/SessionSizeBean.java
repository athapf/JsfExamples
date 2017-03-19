package de.thaso.jd.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

/**
 * SessionSizeBean
 *
 * @author thaler
 * @since 15.03.17
 */
@Named
@ApplicationScoped
public class SessionSizeBean {
    private static final Logger LOG = LoggerFactory.getLogger(SessionSizeBean.class);

    private static final long OBJECT_BASE_SIZE = 16L;
    private static final long ARRAY_BASE_SIZE = 24L;

    private static final long PADDING_SIZE = 8L;
    private static final long PRIMITIV_WITH_PADDING_SIZE = 8L;
    private static final long REFERENCE_WITH_PADDING_SIZE = 8L;

    private static final long BOOLEAN_SIZE = 1L;
    private static final long BYTE_SIZE = 1L;
    private static final long SHORT_SIZE = 2L;
    private static final long CHAR_SIZE = 2L;
    private static final long INTEGER_SIZE = 4L;
    private static final long FLOAT_SIZE = 4L;
    private static final long LONG_SIZE = 8L;
    private static final long DOUBLE_SIZE = 8L;
    private static final long REFERENCE_SIZE = 4L;

    private static final long EMPTY_SIZE = 0L;

    public long calculateHeapSizeOf(final Object object) {
        Set<Object> processedObjects = new HashSet<>();
        return calculateObject(processedObjects, object);
    }

    private long calculateObject(final Set<Object> processedObjects, final Object object) {
        if(object != null) {
            final Class<?> objectClass = object.getClass();
            if(objectClass.equals(boolean.class)
                    || objectClass.equals(byte.class)
                    || objectClass.equals(short.class)
                    || objectClass.equals(int.class)
                    || objectClass.equals(float.class)
                    || objectClass.equals(long.class)
                    || objectClass.equals(double.class)
                    || objectClass.equals(char.class)) {
                return EMPTY_SIZE;
            } else if (objectClass.isArray()) {
                logObjectId(object);
                return calculateArray(processedObjects, object);
            } else {
                logObjectId(object);
                return calculateObjectFields(processedObjects, object);
            }
        }
        return EMPTY_SIZE;
    }

    private boolean toProcesse(final Object object, final Set<Object> processedObjects) {
        final boolean contains = processedObjects.contains(object);
        processedObjects.add(object);
        return !contains;
    }

    private long calculateObjectFields(final Set<Object> processedObjects, final Object object) {
        boolean toProcesse = toProcesse(object, processedObjects);
        if(toProcesse) {
            long objectSize = OBJECT_BASE_SIZE;
            for (Field field : object.getClass().getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    final long size = fieldSizeWithPadding(processedObjects, field, object);
                    logFieldInfo(size, field);
                    objectSize += size;
                }
                logFieldInfo(0, field);
            }
            return objectSize;
        }
        return EMPTY_SIZE;
    }
    
    private long fieldSizeWithPadding(final Set<Object> processedObjects, final Field field, final Object object) {
        final Class<?> fieldType = field.getType();
        if (fieldType.isPrimitive()) {
            return PRIMITIV_WITH_PADDING_SIZE;
        } else if (fieldType.isArray()) {
            try {
                field.setAccessible(true);
                final Object array = field.get(object);
                return REFERENCE_WITH_PADDING_SIZE + calculateArray(processedObjects, array);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return EMPTY_SIZE;
        }
        // default is reference
        try {
            final Object subObject = field.get(object);
            if(subObject != null) {
                return REFERENCE_WITH_PADDING_SIZE + calculateObject(processedObjects, subObject);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return PADDING_SIZE;
    }

    private long calculateArray(final Set<Object> processedObjects, final Object array) {
        boolean toProcesse = toProcesse(array, processedObjects);
        if(array != null && toProcesse) {
            final long arrayLength = Array.getLength(array);
            final long contentSize = calculateArrayContent(processedObjects, array);
            logObjectId(array);
            LOG.info("base={}, array={}, content={}", ARRAY_BASE_SIZE, ((((arrayLength * sizeFactor(array.getClass())) + 7L) / 8L) * 8L), contentSize);
            return ARRAY_BASE_SIZE + contentSize + ((((arrayLength * sizeFactor(array.getClass())) + 7L) / 8L) * 8L);
        }
        return EMPTY_SIZE;
    }

    private long calculateArrayContent(final Set<Object> processedObjects, final Object array) {
        long contentSize = EMPTY_SIZE;
        if(array.getClass().getCanonicalName().endsWith("[][]")) {
            final long arrayLength = Array.getLength(array);
            for (int i = 0; i < arrayLength; i++) {
                // only objects and arrays (no primitives)
                final Object object = Array.get(array, i);
                long size = calculateObject(processedObjects, object);
                contentSize += size;
            }
        } if(array.getClass().isAssignableFrom(Object[].class)) {
            final long arrayLength = Array.getLength(array);
            for (int i = 0; i < arrayLength; i++) {
                final Object object = Array.get(array, i);
                long size = calculateObject(processedObjects, object);
                contentSize += size;
            }
        }
        return contentSize;
    }

    private long sizeFactor(final Class elementType) {
        if (elementType.equals(boolean[].class)
                || elementType.equals(byte[].class)) {
            return BYTE_SIZE;
        } else if (elementType.equals(short[].class)
                || elementType.equals(char[].class)) {
            return SHORT_SIZE;
        } else if (elementType.equals(int[].class)
                || elementType.equals(float[].class)) {
            return INTEGER_SIZE;
        } else if (elementType.equals(long[].class)
                || elementType.equals(double[].class)) {
            return LONG_SIZE;
        }
        // default is reference, also for multi dimensional arrays
        return REFERENCE_SIZE;
    }

    private void logObjectId(final Object object) {
        if(object != null) {
            try {
                final Method hashCodeMethod = Object.class.getDeclaredMethod("hashCode");
                final int objHashCode = (Integer) hashCodeMethod.invoke(object);
                LOG.info("calculate object@{}: {}", Integer.toHexString(objHashCode), object.getClass().getCanonicalName());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void logFieldInfo(final long size, final Field field) {
        LOG.info("size={}, name={}, modifiers={}, type={}, annotaisons={}",
                size,
                field.getName(),
                Modifier.toString(field.getModifiers()),
                field.getType().getSimpleName(),
                field.getDeclaredAnnotations());
    }
}
