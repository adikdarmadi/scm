package com.scm.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AppPermission annotation, digunakan untuk method yang butuh authorized dari database
 * can use in method only.
 * @author Adik
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface  AppPermission {
	String hakAkses() default "*";
	
	String hakMenu() default "*";
}
