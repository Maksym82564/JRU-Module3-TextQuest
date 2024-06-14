package com.javarush.model;

import java.util.ArrayList;
import java.util.List;

public class OptionNode {
    String issue;
    List<Option> options;

    public OptionNode(String issue) {
        this.issue = issue;
        options = new ArrayList<>();
    }

    public String getIssue() {
        return issue;
    }

    public List<Option> getOptions() {
        return options;
    }
}