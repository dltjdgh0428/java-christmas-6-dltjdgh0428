package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.application.EventPlannerService;
import christmas.controller.EventPlannerController;
import christmas.ui.InputView;
import christmas.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        EventPlannerService eventPlannerService = new EventPlannerService();

        EventPlannerController eventPlannerController = new EventPlannerController(eventPlannerService, inputView, outputView);
        eventPlannerController.run();

        Console.close();
    }
}
