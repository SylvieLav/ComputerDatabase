package com.computerDatabase.excilys.dao;

import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.model.*;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.hibernate.*;

@Component
public class ComputerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ComputerDAO() {}

	public Computer create(Computer computer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(computer);
			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return computer;
	}

	public List<Computer> list(long number, long page, String sortElement, String orderBy) {
		QComputer qComputer = QComputer.computer;
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		OrderSpecifier<?> specifier = null;
		if (sortElement.contains("introduced")) {
			if (orderBy.contains("DESC"))
				specifier = qComputer.introduced.desc();
			else
				specifier = qComputer.introduced.asc();
		} else if (sortElement.contains("discontinued")) {
			if (orderBy.contains("DESC"))
				specifier = qComputer.discontinued.desc();
			else
				specifier = qComputer.discontinued.asc();
		} else if (sortElement.contains("companyName")) {
			if (orderBy.contains("DESC"))
				specifier = qComputer.company().name.desc();
			else
				specifier = qComputer.company().name.asc();
		} else {
			if (orderBy.contains("DESC"))
				specifier = qComputer.name.desc();
			else
				specifier = qComputer.name.asc();
		}

		try {
			HibernateQuery<Computer> hComputers = null;
			if (number == -1)
				hComputers = query.selectFrom(qComputer).orderBy(specifier);
			else
				hComputers = query.selectFrom(qComputer).orderBy(specifier).limit(number).offset((page-1)*number);
			return hComputers.fetch();
		} finally {
			session.close();
		}
	}

	public List<Computer> listBySearch(long number, long page, String sortElement, String orderBy, String search) {
		QComputer qComputer = QComputer.computer;
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		try {
			HibernateQuery<Computer> hComputers = query.selectFrom(qComputer).where(qComputer.company().name.like("%" + search + "%").or(qComputer.name.like("%" + search + "%")));
			return hComputers.fetch();
		} finally {
			session.close();
		}
	}

	public Optional<Computer> listDetails(long id) {
		QComputer qcomputer = QComputer.computer;
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		try {
			HibernateQuery<Computer> hComputer = query.selectFrom(qcomputer).where(qcomputer.id.eq(id));
			return Optional.ofNullable(hComputer.fetchOne());
		} finally {
			session.close();
		}
	}
	
	public Computer update(Computer computer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.update(computer);
			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return computer;
	}

	public long delete(long id) {
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		QComputer qcomputer = QComputer.computer;
		try { 
			query.delete(qcomputer).where(qcomputer.id.eq(id)).execute();
		} finally {
			session.close();
		}

		return id;
	}
}
