package de.thaso.jd.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * SessionSizeBean
 *
 * @author thaler
 * @since 15.03.17
 */
@Named
@ApplicationScoped
public class SessionSizeBean {
    public static final Logger LOG = LoggerFactory.getLogger(SessionSizeBean.class);

    public long calculateHeapSizeOf(final Object object) {
        try {
            final Method hashCodeMethod = Object.class.getDeclaredMethod("hashCode");
            final int objHashCode = (Integer) hashCodeMethod.invoke(object);
            LOG.info("calculate object: {}", objHashCode);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return calculateObjectSize(object);
    }

    private long calculateObjectSize(final Object object) {

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
                return 0L;
            } else if (objectClass.isArray()) {
                return calculateArray(object);
            } else {
                return 16L + calculateObjectFields(object);
            }
        }
        return 0L;
    }

    private long calculateObjectFields(final Object object) {
        long objectSize = 0L;
        for(Field field : object.getClass().getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                final long size = fieldSizeWithPadding(field, object);
                logFieldInfo(size, field);
                objectSize += size;
            }
            logFieldInfo(0, field);
        }
        return objectSize;
    }
    
    private long fieldSizeWithPadding(final Field field, final Object object) {
        final Class<?> fieldType = field.getType();
        if (fieldType.isPrimitive()) {
            return 8L;
        } else if (fieldType.isArray()) {
            try {
                field.setAccessible(true);
                final Object array = field.get(object);
                return 8L + calculateArray(array);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return 0L;
        }
        // default is reference
        try {
            final Object subObject = field.get(object);
            if(subObject != null) {
                return 8L + calculateHeapSizeOf(subObject);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 8L;
    }

    private long calculateArray(final Object array) {
        if(array != null) {
            final long arrayLength = Array.getLength(array);
            final long contentSize = calculateArrayContent(array);
            LOG.info("array: {} [{}]", array.getClass().getCanonicalName(), arrayLength);
            return 24L + contentSize + ((((arrayLength * sizeFactor(array.getClass())) + 7L) / 8L) * 8L);
        }
        return 0L;
    }

    private long calculateArrayContent(final Object array) {
        long contentSize = 0L;
        LOG.info("calculate array {}", array.getClass().getCanonicalName());
        if(array.getClass().getCanonicalName().endsWith("[][]")) {
            final long arrayLength = Array.getLength(array);
            for (int i = 0; i < arrayLength; i++) {
                // only objects and arrays (no primitives)
                final Object object = Array.get(array, i);
                contentSize += calculateObjectSize(object);
            }
        }
        return contentSize;
    }

    private long sizeFactor(final Class elementType) {
        if (elementType.equals(boolean[].class)
                || elementType.equals(byte[].class)) {
            return 1L;
        } else if (elementType.equals(short[].class)
                || elementType.equals(char[].class)) {
            return 2L;
        } else if (elementType.equals(int[].class)
                || elementType.equals(float[].class)) {
            return 4L;
        } else if (elementType.equals(long[].class)
                || elementType.equals(double[].class)) {
            return 8L;
        }
        // default is reference, also for multi dimensional arrays
        return 4L;
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
