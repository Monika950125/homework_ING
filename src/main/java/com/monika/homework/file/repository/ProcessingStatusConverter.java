package com.monika.homework.file.repository;

import com.monika.homework.file.domain.ProcessingStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProcessingStatusConverter implements AttributeConverter<ProcessingStatus, String> {

    @Override
    public String convertToDatabaseColumn(ProcessingStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public ProcessingStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(ProcessingStatus.values())
                .filter(c -> c.name().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
