package travel_insurance.core.service;

import org.springframework.stereotype.Component;
import travel_insurance.core.jpa.TravelCalculatePremiumService;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response  = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());

        var daysBetween = calculateAgreementDurationInDays(request);
        response.setAgreementPrice(new BigDecimal(daysBetween));

        return response;
    }

    private long calculateAgreementDurationInDays(TravelCalculatePremiumRequest request) {
        long diff = request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}
