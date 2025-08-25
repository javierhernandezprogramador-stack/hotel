package org.jadez.apiservlet.webapp.hotel.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

@Transactional
@Interceptor
public class TransactionalInterceptor {

    @Inject
    private EntityManager em;

    @AroundInvoke
    private Object transactional(InvocationContext invocation) throws Exception {
        try {

            em.getTransaction().begin();

            Object resultado = invocation.proceed();

            em.getTransaction().commit();

            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
