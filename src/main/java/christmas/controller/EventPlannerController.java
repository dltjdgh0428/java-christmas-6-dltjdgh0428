package christmas.controller;

import christmas.application.EventPlannerService;
import christmas.domain.vo.DateVO;
import christmas.domain.vo.OrderVO;
import christmas.dto.DateDTO;
import christmas.dto.OrderDTO;
import christmas.ui.ExceptionHandler;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.concurrent.atomic.AtomicReference;

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
        processEventPlanning();
    }

    private void processEventPlanning() {
        DateDTO dateDTO = processDateInput();
        OrderDTO orderDTO = processOrderInput();

        outputView.printEventBenefitPreview(dateDTO.day());
        outputView.printOrderMenu(orderDTO);
        outputView.printTotalOrderAmountBeforeDiscount(eventPlannerService.totalBeforeDiscount(orderDTO));
        outputView.printGiftMenu(eventPlannerService.determineGiftEvent(orderDTO));
        outputView.printBenefitDetails(eventPlannerService.calculateDiscountDetails(orderDTO, dateDTO.day()));
        outputView.printTotalBenefitAmount(eventPlannerService.calculateTotalBenefit(orderDTO, dateDTO.day()));
        outputView.printExpectedPaymentAfterDiscount(eventPlannerService.calculateFinalAmount(orderDTO, dateDTO.day()));
        outputView.printEventBadge(eventPlannerService.determineEventBadge(orderDTO, dateDTO.day()));
    }

    private DateDTO processDateInput() {
        DateVO dateVO = getDateVO();
        return eventPlannerService.convertToDateDto(dateVO);
    }

    private OrderDTO processOrderInput() {
        OrderVO orderVO = getOrderVO();
        return eventPlannerService.convertToOrderDTO(orderVO);
    }

    private DateVO getDateVO() {
        AtomicReference<DateVO> dateVORef = new AtomicReference<>();
        ExceptionHandler.handleException(() -> {
            dateVORef.set(new DateVO(inputView.askForDate()));
        }, outputView);
        return dateVORef.get();
    }

    private OrderVO getOrderVO() {
        AtomicReference<OrderVO> orderVORef = new AtomicReference<>();
        ExceptionHandler.handleException(() -> {
            orderVORef.set(new OrderVO(inputView.askForOrder()));
        }, outputView);
        return orderVORef.get();
    }
}
