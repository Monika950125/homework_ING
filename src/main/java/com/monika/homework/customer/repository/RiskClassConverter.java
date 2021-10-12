package com.monika.homework.customer.repository;

import com.monika.homework.customer.domain.RiskClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RiskClassConverter implements AttributeConverter<RiskClass, String> {

    @Override
    public String convertToDatabaseColumn(RiskClass attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public RiskClass convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(RiskClass.values())
                .filter(c -> c.name().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}