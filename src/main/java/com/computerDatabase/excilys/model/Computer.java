package com.computerDatabase.excilys.model;

import java.time.LocalDateTime;

public class Computer {
	private Company company;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private long id;
	private String name;

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

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public static class ComputerBuilder {
		private Company builderCompany;
		private LocalDateTime builderIntroduced;
		private LocalDateTime builderDiscontinued;
		private long builderId;
		private String builderName;

		public ComputerBuilder id(long id) {
			builderId = id;
			
			return this;
		}

		public ComputerBuilder(String name) {
			builderName = name;
		}

		public ComputerBuilder company(Company company) {
			if (company != null) {
				builderCompany = company;
			}
			
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

		public Computer build() {
			return new Computer(this);
		}
	}
}
