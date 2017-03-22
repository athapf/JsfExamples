package de.thaso.jd.web.utils.demo;

/**
 * SimpleClassPrimitivesAndArray
 *
 * @author thaler
 * @since 15.03.17
 */
public class SimpleClassWithInheritance extends SimpleClassWithIntArray {

    private String name;
    private Integer age;

    public SimpleClassWithInheritance() {
    }

    public SimpleClassWithInheritance(final int[] arrayOfInt, final String name, final Integer age) {
        super(arrayOfInt);
        this.name = name;
        this.age = age;
    }
}
