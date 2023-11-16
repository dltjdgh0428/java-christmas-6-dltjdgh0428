package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.contents.Prompts;

public class InputView {
    public String askForDate() {
        System.out.println(Prompts.ASK_FOR_DATE.getMessage());
        return Console.readLine();
    }

    public String askForOrder() {
        System.out.println(Prompts.ASK_FOR_ORDER.getMessage());
        return Console.readLine();
    }
}
