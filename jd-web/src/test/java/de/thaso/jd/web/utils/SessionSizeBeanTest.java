package de.thaso.jd.web.utils;

import de.thaso.jd.web.utils.demo.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * SessionSizeBeanTest
 *
 * @author thaler
 * @since 15.03.17
 */
public class SessionSizeBeanTest {

    private SessionSizeBean underTest;
    
    @Before
    public void setUp() {
        underTest = new SessionSizeBean();
    }

    @Test
    public void testCalculateHeapSizeOf_Object() {
        assertThat(underTest.calculateHeapSizeOf(new Object()), is(16L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithStatic() {
        assertThat(underTest.calculateHeapSizeOf(new SimpleClassWithStatic()), is(16L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithInt() {
        assertThat(underTest.calculateHeapSizeOf(new SimpleClassWithInt()), is(24L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithChar() {
        assertThat(underTest.calculateHeapSizeOf(new SimpleClassWithChar()), is(24L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithArrayNull() {
        assertThat(underTest.calculateHeapSizeOf(new SimpleClassWithByteArray()), is(24L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithObject() {
        assertThat(underTest.calculateHeapSizeOf(new SimpleClassWithObject()), is(24L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithFilledObject() {
        assertThat(underTest.calculateHeapSizeOf(new SimpleClassWithObject(new SimpleClassWithInt())), is(48L));
    }

    @Test
    public void testCalculateHeapSizeOf_StringClassWithSmart() {
        assertThat(underTest.calculateHeapSizeOf(new String("Smart")), is(72L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithEmptyByteArray() {
        // given
        final byte[] bytes = new byte[0];
        final SimpleClassWithByteArray objectWithArray = new SimpleClassWithByteArray(bytes);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithArray), is(48L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithFilledByteArray() {
        // given
        final byte[] bytes = new byte[10];
        final SimpleClassWithByteArray objectWithArray = new SimpleClassWithByteArray(bytes);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithArray), is(64L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithFilledIntArray() {
        // given
        final int[] ints = new int[5];
        final SimpleClassWithIntArray objectWithArray = new SimpleClassWithIntArray(ints);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithArray), is(72L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithFilledObjectArrayOfArray() {
        // given
        final Object[][] objects = new Object[2][3];
        objects[0][1] = new String("foo");
        objects[1][2] = new String("bar x2");
        final SimpleClassWithObjectArray objectWithArray = new SimpleClassWithObjectArray(objects);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithArray), is(272L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithFilledObjectArrayOfArrayUsingIdenticalElements() {
        // given
        final String element = new String("foo bar");
        final Object[][] objects = new Object[2][3];
        objects[0][1] = element;
        objects[1][0] = element;
        objects[1][2] = element;
        final SimpleClassWithObjectArrayOfArray objectWithArray = new SimpleClassWithObjectArrayOfArray(objects);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithArray), is(208L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithDoubleFilledObjectArrayOfArrayUsingIdenticalElements() {
        // given
        final String element = new String("foo bar");
        final Object[][] objects = new Object[2][3];
        objects[0][1] = element;
        objects[1][0] = element;
        objects[1][2] = element;
        final SimpleClassWithDoubleObjectArrayOfArray objectWithDoubleArray = new SimpleClassWithDoubleObjectArrayOfArray(objects, objects);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithDoubleArray), is(216L));
    }
}