package com.computerDatabase.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company", schema = "computer-database-db")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	
	public Company() {}
	
	private Company(CompanyBuilder builder) {
		this.id = builder.builderId;
		this.name = builder.builderName;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static class CompanyBuilder {
		private long	builderId;
		private String	builderName;
		
		public CompanyBuilder(long id) {
			builderId = id;
		}
		
		public CompanyBuilder name(String name) {
			builderName = name;
			return this;
		}
		
		public Company build() {
			return new Company(this);
		}
	}
}
