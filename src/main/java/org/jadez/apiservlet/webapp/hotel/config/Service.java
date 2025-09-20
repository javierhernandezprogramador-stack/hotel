package org.jadez.apiservlet.webapp.hotel.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.jadez.apiservlet.webapp.hotel.interceptors.Logging;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Logging
@Named
@Stereotype
@Retention(RUNTIME)
@Target({TYPE})
public @interface Service {
}
