package christmas.controller;

import christmas.application.EventPlannerService;
import christmas.domain.vo.DateVO;
import christmas.domain.vo.OrderVO;
import christmas.dto.DateDTO;
import christmas.dto.OrderDTO;
import christmas.ui.InputView;
import christmas.ui.OutputView;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EventPlannerService eventPlannerService;

    public EventPlannerController(EventPlannerService eventPlannerService, InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventPlannerService = eventPlannerService;
    }

    public void run() {
        outputView.printWelcomeMessage();
        DateVO dateVO = new DateVO(inputView.askForDate());
        OrderVO orderVO = new OrderVO(inputView.askForOrder());
        DateDTO dateDTO = eventPlannerService.convertToDateDto(dateVO);
        OrderDTO orderDTO = eventPlannerService.convertToOrderDTO(orderVO);
        outputView.printEventBenefitPreview(dateDTO.day());
        outputView.printOrderMenu(orderDTO);
        outputView.printTotalOrderAmountBeforeDiscount(eventPlannerService.totalBeforeDiscount(orderDTO));
        outputView.printGiftMenu(eventPlannerService.determineGiftEvent(orderDTO));
        outputView.printBenefitDetails(eventPlannerService.calculateDiscountDetails(orderDTO, dateDTO.day()));
        outputView.printTotalBenefitAmount(eventPlannerService.totalDiscount(orderDTO, dateDTO.day()));
        outputView.printExpectedPaymentAfterDiscount(eventPlannerService.calculateFinalAmount(orderDTO, dateDTO.day()));
        outputView.printEventBadge(eventPlannerService.determineEventBadge(orderDTO, dateDTO.day()));
    }


}
