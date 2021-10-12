package com.monika.homework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DateParserTest {

    @Test
    public void parseTest(){
        //Given
        String txt = "2021-10-11";
        LocalDate expectedDate = LocalDate.of(2021,10,11);

        //When
        LocalDate date = DateParser.parse(txt);

        //Then
        Assertions.assertEquals(expectedDate,date);
    }

}