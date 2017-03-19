package de.thaso.jd.web.utils;

import de.thaso.jd.web.utils.demo.SimpleClassWithByteArray;
import de.thaso.jd.web.utils.demo.SimpleClassWithChar;
import de.thaso.jd.web.utils.demo.SimpleClassWithInt;
import de.thaso.jd.web.utils.demo.SimpleClassWithIntArray;
import de.thaso.jd.web.utils.demo.SimpleClassWithObject;
import de.thaso.jd.web.utils.demo.SimpleClassWithObjectArray;
import de.thaso.jd.web.utils.demo.SimpleClassWithShortArrayOfArray;
import de.thaso.jd.web.utils.demo.SimpleClassWithStatic;
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
        assertThat(underTest.calculateHeapSizeOf(new String("Smart")), is(80L));
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
    public void testCalculateHeapSizeOf_SimpleClassWithFilledObjectArray() {
        // given
        final Object[] objects = new Object[7];
        final SimpleClassWithObjectArray objectWithArray = new SimpleClassWithObjectArray(objects);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithArray), is(80L));
    }

    @Test
    public void testCalculateHeapSizeOf_SimpleClassWithFilledArrayOfArray() {
        // given
        final short[][] shorts = new short[3][5];
        final SimpleClassWithShortArrayOfArray objectWithArray = new SimpleClassWithShortArrayOfArray(shorts);
        // when
        assertThat(underTest.calculateHeapSizeOf(objectWithArray), is(184L));
    }
}