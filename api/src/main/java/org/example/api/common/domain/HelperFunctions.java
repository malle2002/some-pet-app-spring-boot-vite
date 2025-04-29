package org.example.api.common.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class HelperFunctions {
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public static int GetAge(Date date) {
        LocalDate toLocalDate = convertToLocalDateViaInstant(date);
        return Period.between(toLocalDate, LocalDate.now()).getYears();
    }
}
