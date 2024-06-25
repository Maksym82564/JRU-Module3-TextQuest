package com.javarush.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class OptionNode {
    String issue;
    List<Option> options;

    public OptionNode(String issue) {
        this.issue = issue;
        options = new ArrayList<>();
    }

}