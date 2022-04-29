package com.embedica.cardirectory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Statistic {

    private String count;
    private String firstWriteDate;
    private String lastWriteDate;
    private String idLastEntry;

    public static Statistic of(String count,
                               String firstWriteDate,
                               String lastWriteDate,
                               String idLastEntry
    ) {
        Statistic statistic = new Statistic();
        statistic.count = count;
        statistic.firstWriteDate = firstWriteDate;
        statistic.lastWriteDate = lastWriteDate;
        statistic.idLastEntry = idLastEntry;
        return statistic;
    }
}
