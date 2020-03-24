package com.io.fizmat.xlsreader.model;

import java.util.List;

public class Curs {
    private String cursTitle;
    private List<Group> groupList;

    public String getCursTitle() {
        return cursTitle;
    }

    public void setCursTitle(String cursTitle) {
        this.cursTitle = cursTitle;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}
