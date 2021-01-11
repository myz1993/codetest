package com.utu.codetest.converter;

import com.utu.codetest.utils.DateUtil;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.Date;

@Convert
@RequiredArgsConstructor
public class CSVDateConverter implements AttributeConverter<Date, String> {
    private final DateUtil dateUtil;

    @Override
    public String convertToDatabaseColumn(Date date) {
        return dateUtil.dateToString(date);
    }

    @Override
    public Date convertToEntityAttribute(String s) {
        return dateUtil.stringToDate(s);
    }
}
