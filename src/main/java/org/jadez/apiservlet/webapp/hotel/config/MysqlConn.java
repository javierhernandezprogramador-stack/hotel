package org.jadez.apiservlet.webapp.hotel.config;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR})
public @interface MysqlConn {

}
