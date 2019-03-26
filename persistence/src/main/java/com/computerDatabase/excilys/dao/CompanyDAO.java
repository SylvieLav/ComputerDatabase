package com.computerDatabase.excilys.dao;

import com.computerDatabase.excilys.model.Company;
import com.computerDatabase.excilys.model.QCompany;
import com.querydsl.jpa.hibernate.*;

import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Company> list() {
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		
		try {
			HibernateQuery<Company> hCompanies = query.selectFrom(QCompany.company);
			return hCompanies.fetch();
		} finally {
			session.close();
		}
	}

	public Optional<Company> getCompanyById(long idCompany) {
		QCompany qcompany = QCompany.company;
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		try {
			HibernateQuery<Company> hCompany = query.selectFrom(qcompany).where(qcompany.id.eq(idCompany));
			return Optional.ofNullable(hCompany.fetchOne());
		} finally {
			session.close();
		}
	}

	public long delete(long id) {
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		QCompany qcompany = QCompany.company;
		try { 
			query.delete(qcompany).where(qcompany.id.eq(id)).execute();
		} finally {
			session.close();
		}

		return id;
	}
}
