package de.thaso.jd.web.it.base;

/**
 * PageObjectComponent
 *
 * @author thaler
 * @since 27.02.17
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageObjectComponent {
}
