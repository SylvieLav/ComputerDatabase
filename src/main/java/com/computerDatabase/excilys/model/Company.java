package com.computerDatabase.excilys.model;

public class Company {
	private final long		id;
	private final String	name;
	
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
		private final	long	builderId;
		private			String	builderName;
		
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
