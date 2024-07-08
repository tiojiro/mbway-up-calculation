package com.tiojiro.upcalculation.model;

import java.time.LocalDateTime;

public class Constants {
    public static final LocalDateTime dailyStartDate = LocalDateTime.of(2021,9,22,7,12);
    public static final LocalDateTime weeklyStartDate = LocalDateTime.of(2021,9,25,21,45);
    public static final LocalDateTime monthlyStartDate = LocalDateTime.of(2021,9,25,13,0);
    public static final String DATE_PATTERN = "dd/MM/yyyy - HH:mm";
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int SIXTEEN = 16;
    public static final int TWENTY_FOUR = 24;
    public static final int TWENTY_THREE = 23;
    public static final int THIRTY_SIX = 36;
    public static final int FIFTY_NINE = 59;
    public static final int NINE_AM_O_CLOCK = 9;
    public static final int ELEVEN_AM_O_CLOCK = 11;
    public static final int ONE_PM_O_CLOCK = 13;
    public static final int THREE_PM_O_CLOCK = 15;
    public static final int FIVE_PM_O_CLOCK = 17;
    public static final int SEVEN_PM_O_CLOCK = 19;
    public static final int NINE_PM_O_CLOCK = 21;
    public static final int FIFTEEN_MINUTES = 15;
    public static final int FORTY_FIVE_MINUTES = 45;
    public static final String LINE_BREAK = "<br>";
    public static final String README_TEMPLATE = "<table>" +
                                                    "<tr>" +
                                                        "<td>Diário</td>" +
                                                        "<td>Semanal</td>" +
                                                        "<td>Mensal</td>" +
                                                    "</tr>" +
                                                    "<tr>" +
                                                        "<td valign='top'>" +
                                                            "{0}" +
                                                        "</td>" +
                                                        "<td valign='top'>" +
                                                            "{1}" +
                                                        "</td>" +
                                                        "<td valign='top'>" +
                                                            "{2}" +
                                                        "</td>" +
                                                    "</tr>" +
                                                 "</table>";
}
