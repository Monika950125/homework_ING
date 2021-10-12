package com.monika.homework.mail.repository;

import com.monika.homework.customer.domain.BusinessType;
import com.monika.homework.mail.domain.MailType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class MailTypeConverter implements AttributeConverter<MailType, String> {

    @Override
    public String convertToDatabaseColumn(MailType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public MailType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(MailType.values())
                .filter(c -> c.name().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
