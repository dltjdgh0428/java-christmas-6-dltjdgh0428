package christmas.application;

import christmas.domain.Date;
import christmas.dto.DateDTO;

public class EventPlannerService {




    public DateDTO convertToDateDto(Date date) {
        return new DateDTO(date.getDay());
    }
}
