package br.senai.sc.ti20122n.pw.mensagensweb.util;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.internal.jaxb.mapping.hbm.JaxbPrimitiveArrayElement;

@WebFilter(servletNames = "Faces Servlet")
public class JPAFilter implements Filter {
	EntityManagerFactory entityManagerFactory;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("MensagensWeb");
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();

		filterChain.doFilter(servletRequest, servletResponse);
		try {
			entityManager.getTransaction().commit();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void destroy() {
		entityManagerFactory.close();

	}
}
