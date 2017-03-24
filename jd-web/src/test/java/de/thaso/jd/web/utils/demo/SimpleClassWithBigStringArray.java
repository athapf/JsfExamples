package de.thaso.jd.web.utils.demo;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * SimpleClassWithObjectArray
 *
 * @author thaler
 * @since 16.03.17
 */
public class SimpleClassWithBigStringArray {

    private String[] arrayOfString;

    public SimpleClassWithBigStringArray() {
        init(500);
    }

    public SimpleClassWithBigStringArray(final int count) {
        init(count);
    }

    private void init(final int count) {
        arrayOfString = new String[count];
        for(int i = 0; i < count; i++) {
            arrayOfString[i] = RandomStringUtils.randomAlphanumeric(20);
        }
    }
}
