package com.io.fizmat.xlsreader.model;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private List<String> timetable = new ArrayList<>();

    public List<String> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<String> timetable) {
        this.timetable = timetable;
    }

    public void addTimetable(String timetableString)
    {
        timetable.add(timetableString);
    }
}
