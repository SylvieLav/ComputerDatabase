package com.computerDatabase.excilys.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "computer", schema = "computer-database-db")
public class Computer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "introduced")
	private LocalDateTime introduced;
	
	@Column(name = "discontinued")
	private LocalDateTime discontinued;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	public Computer() {}
	
	private Computer(ComputerBuilder builder) {
		this.id = builder.builderId;
		this.name = builder.builderName;
		this.introduced = builder.builderIntroduced;
		this.discontinued = builder.builderDiscontinued;
		this.company = builder.builderCompany;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public LocalDateTime getIntroduced() {
		return introduced;
	}
	
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}
	
	public static class ComputerBuilder {
		private String			builderName;
		private long			builderId;
		private Company			builderCompany;
		private LocalDateTime	builderIntroduced;
		private LocalDateTime	builderDiscontinued;
		
		public ComputerBuilder(String name) {
			builderName = name;
		}
		
		public ComputerBuilder id(long id) {
			builderId = id;
			return this;
		}
		
		public ComputerBuilder introduced(LocalDateTime introduced) {
			if (introduced != null) {
				builderIntroduced = introduced;
			}
			return this;
		}
		
		public ComputerBuilder discontinued(LocalDateTime discontinued) {
			if (discontinued != null) {
				builderDiscontinued = discontinued;
			}
			return this;
		}
		
		public ComputerBuilder company(Company company) {
			if (company != null) {
				builderCompany = company;
			}
			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}
	}
}
