package travel_insurance.core.service;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;

import java.util.concurrent.TimeUnit;
@Component
public class DateTimeService {

    public long getDaysBetween(TravelCalculatePremiumRequest request) {
        long diff = request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
