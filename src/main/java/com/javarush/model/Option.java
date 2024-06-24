package com.javarush.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Option {
    private final String text;
    private final int nextOptionNodeIndex;
    private final OptionType optionType;
    private final String result;

    public Option(String text, int nextOptionNodeIndex) {
        this.text = text;
        this.nextOptionNodeIndex = nextOptionNodeIndex;
        this.optionType = OptionType.REGULAR;
        this.result = "CONTINUES";
    }

    public Option(String text, OptionType optionType, String result) {
        this.optionType = optionType;
        this.text = text;
        this.nextOptionNodeIndex = -1;
        this.result = result;
    }
}