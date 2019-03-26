package com.computerDatabase.excilys.dao;

import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.computerDatabase.excilys.model.QUser;
import com.computerDatabase.excilys.model.User;
import com.querydsl.jpa.hibernate.*;

@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	public UserDAO() {}

	public Optional<User> getUserByName(String name) {
		QUser quser = QUser.user;
		Session session = sessionFactory.openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		try {
			HibernateQuery<User> hComputer = query.selectFrom(quser).where(quser.name.eq(name));
			return Optional.ofNullable(hComputer.fetchOne());
		} finally {
			session.close();
		}
	}
}
