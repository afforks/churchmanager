package br.com.churchmanager.jsf.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter to provide Java 8 Date/Time API Support to JPA
 * 
 * @author Juneau
 */
@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {
	@Override
	public Date convertToDatabaseColumn(LocalDate entityValue) {
		LocalTime time = LocalTime.now();
		Instant instant = time.atDate(entityValue).atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date databaseValue) {
		Instant instant = Instant.ofEpochMilli(databaseValue.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
}