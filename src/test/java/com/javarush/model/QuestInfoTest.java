package com.javarush.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestInfoTest {
    @Test
    public void getOptionFromNodes_SpecificOption() {
        Option expectedOption = new Option("Відмовитися підніматися на місток.", OptionType.LOSS,
                "Ви не пішли на переговори. Поразка.");
        int nodeIndex = 1;
        int optionIndex = 1;
        QuestInfo instance = QuestInfo.getInstance();
        Option actualOption = instance.getOptionFromNodes(nodeIndex, optionIndex);
        Assertions.assertEquals(expectedOption, actualOption, "Option should match the expected one.");
    }
}