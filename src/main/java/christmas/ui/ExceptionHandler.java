package christmas.ui;

public class ExceptionHandler {
    public static void handleException(Runnable task, OutputView outputView) {
        while (true) {
            try {
                task.run();
                break;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}