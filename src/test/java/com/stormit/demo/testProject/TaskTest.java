package com.stormit.demo.testProject;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void simpleTest() {
        int expected = 10;
        int actual = 10;

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddTwoIntegers() {
        // given
        int expectedResult = 110;

        int variable1 = 10;
        int variable2 = 100;

        // when
        int result = add(variable1, variable2);

        // then
        assertEquals(expectedResult, result);
    }

    int add(int a, int b) {
        return a + b;
    }

    @Test
    void shouldReturnFormattedDate() {
        // given
        DateFormat dateFormat = new SimpleDateFormat();
        Date date = new Date(2019, 1, 10);

        // when
        String formattedDate = dateFormat.format(date);

        // then
        assertEquals(formattedDate, formattedDate);
    }
/*
    @Test
    void shouldAddTwoIntegersAdvanced() {
        // given
        int a = 2_147_483_647;
        int b = 1;

        // when
        int result = a + b;

        // then
        assertEquals(___, result); // -2_147_483_648
        assertEquals(___, Integer.toBinaryString(a)); // 1111111111111111111111111111111
        assertEquals(___, Integer.toBinaryString(result)); // 10000000000000000000000000000000
    }  */
}
