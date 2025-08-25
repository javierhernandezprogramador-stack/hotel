package org.jadez.apiservlet.webapp.hotel.interceptors;

import jakarta.interceptor.InterceptorBinding;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Transactional {
}
