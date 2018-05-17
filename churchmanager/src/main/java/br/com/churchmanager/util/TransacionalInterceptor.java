package br.com.churchmanager.util;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.churchmanager.util.Transacional;

@Interceptor
@Transacional
public class TransacionalInterceptor implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
//		EntityTransaction trx = this.manager.getTransaction();
//		boolean criador = false;
//
//		Object arg5;
//		try {
//			if (!trx.isActive()) {
//				trx.begin();
//				trx.rollback();
//				trx.begin();
//				criador = true;
//			}
//
//			arg5 = context.proceed();
//		} catch (Exception arg8) {
//			if (trx != null && criador) {
//				trx.rollback();
//			}
//
//			throw arg8;
//		} finally {
//			if (trx != null && trx.isActive() && criador) {
//				trx.commit();
//			}
//
//		}
//
//		return arg5;
		EntityTransaction tx = manager.getTransaction();
		Object resultado = null;
		try {
			tx.begin(); // inicia transação
			resultado = context.proceed();
			tx.commit(); // comita transação
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return resultado;
	}
}