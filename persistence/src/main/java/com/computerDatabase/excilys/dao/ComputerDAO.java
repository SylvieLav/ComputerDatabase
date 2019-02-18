package com.computerDatabase.excilys.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.model.QComputer;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

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

	public List<Computer> list(long number, long page, String sortElement, String order) {
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		try {
			HibernateQuery<Computer> hComputers = query.selectFrom(QComputer.computer);
			return hComputers.fetch();
		} finally {
			session.close();
		}
	}
	
	public List<Computer> listBySearch(long number, long page, String sortElement, String order, String filter) {
		QComputer qcomputer = QComputer.computer;
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		try {
			HibernateQuery<Computer> hComputers = query.selectFrom(qcomputer).where(
					qcomputer.company.name.like("%" + filter + "%").or(qcomputer.name.like("%" + filter + "%")));
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