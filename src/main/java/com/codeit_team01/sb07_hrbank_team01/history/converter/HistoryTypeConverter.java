package com.codeit_team01.sb07_hrbank_team01.history.converter;

import com.codeit_team01.sb07_hrbank_team01.history.entity.HistoryType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HistoryTypeConverter implements AttributeConverter<HistoryType, String> {
    @Override
    public String convertToDatabaseColumn(HistoryType attribute) {
        if(attribute == null) return null;
        return switch (attribute){
            case EMPLOYEE_CREATE -> "CREATED";
            case EMPLOYEE_UPDATE -> "UPDATED";
            case EMPLOYEE_DELETE -> "DELETED";
        };
    }
    @Override
    public HistoryType convertToEntityAttribute(String dbData) {
        if(dbData == null) return null;
        return switch(dbData){
            case "CREATED" -> HistoryType.EMPLOYEE_CREATE;
            case "UPDATED" -> HistoryType.EMPLOYEE_UPDATE;
            case "DELETED" -> HistoryType.EMPLOYEE_DELETE;
            default -> null;
        };
    }
}
