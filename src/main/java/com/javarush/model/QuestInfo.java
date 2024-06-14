package com.javarush.model;

import java.util.ArrayList;
import java.util.List;

public class QuestInfo {
    private static QuestInfo questInfo;
    private final List<OptionNode> questOptionNodes;
    private final String questDescription;

    private QuestInfo() {
        questDescription = "Втрачачи пам’ять, ви знаходитесь в епіцентрі неймовірної пригоди. " +
                "Перед вами стоїть вибір — прийняти виклик НЛО чи відмовитися. " +
                "Ваша відповідь визначачить долю героя. " +
                "Піднявшись на місток, вас чекає питання про вашу ідентичність. " +
                "Чи розкриєте ви правду? " +
                "Кожен вибір веде до виграшу чи програшу, впливаючи на кінцевий результат цього захоплюючого квесту.";
        questOptionNodes = new ArrayList<>();

        OptionNode optionNode = new OptionNode("Ви втрачаєте пам'ять. Прийняти виклик НЛО ?");
        List<Option> options = optionNode.getOptions();
        options.add(new Option("Прийняти виклик.", 1));
        options.add(new Option("Відхилити виклик.", OptionType.LOSS,
                        "Ви відхилили виклик. Поразка."));
        questOptionNodes.add(0, optionNode);

        optionNode = new OptionNode("Ви прийняли виклик. Піднятися на капітанський місток ?");
        options = optionNode.getOptions();
        options.add(new Option("Піднятися на місток.", 2));
        options.add(new Option("Відмовитися підніматися на місток.", OptionType.LOSS,
                        "Ви не пішли на переговори. Поразка."));
        questOptionNodes.add(1, optionNode);

        optionNode = new OptionNode("Ви піднялися на місток. Хто ви?");
        options = optionNode.getOptions();
        options.add(new Option("Розповісти правду про себе.", OptionType.WIN,
                        "Вас повернули додому. Перемога."));
        options.add(new Option("Збрехати про себе.", OptionType.LOSS,
                        "Ваша брехня була викрита. Поразка."));
        questOptionNodes.add(2, optionNode);
    }

    public static String getQuestResult(Option option) {
        return option.getResult();
    }

    public static QuestInfo getInstance() {
        if (questInfo == null) {
            questInfo = new QuestInfo();
        }
        return questInfo;
    }

    public static Option getOptionFromNodes(int nodeIndex, int optionIndex) {
        return QuestInfo.getOptionNodes().get(nodeIndex).getOptions().get(optionIndex);
    }

    public static String getQuestDescription() {
        return getInstance().questDescription;
    }

    public static List<OptionNode> getOptionNodes() {
        return getInstance().questOptionNodes;
    }
}