package travel_insurance.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

@Component
public class TravelPremiumUnderwriting {

    @Autowired private DateTimeService service;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        var daysBetween = service.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }
}
