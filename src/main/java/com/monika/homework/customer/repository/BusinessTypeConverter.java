package com.monika.homework.customer.repository;

import com.monika.homework.customer.domain.BusinessType;
import com.monika.homework.customer.domain.CustomerType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class BusinessTypeConverter implements AttributeConverter<BusinessType, String> {

    @Override
    public String convertToDatabaseColumn(BusinessType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public BusinessType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(BusinessType.values())
                .filter(c -> c.name().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
