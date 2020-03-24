package com.io.fizmat.xlsreader.model;

import java.util.List;

public class Group {
    private String nameGroup;
    private List<Day> dayList;

    public Group(String nameGroup)
    {
        this.nameGroup = nameGroup;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public void addTimeTableString(Integer dayNumber,String string)
    {
        Day day = dayList.get(dayNumber);
      //  System.out.println(nameGroup + " " + string);
        day.addTimetable(string);
    }
}
