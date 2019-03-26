package com.computerDatabase.excilys.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.*;

@Converter
public class TimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
		return entityValue == null ? null : Timestamp.valueOf(entityValue);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbValue) {
		return dbValue == null ? null : dbValue.toLocalDateTime(); 
	}

}
