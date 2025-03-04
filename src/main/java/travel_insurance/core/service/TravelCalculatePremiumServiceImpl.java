package travel_insurance.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.jpa.TravelCalculatePremiumService;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.TravelCalculatePremiumResponse;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.validatorMistakes.TravelCalculatePremiumValidator;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired private DateTimeService dateTimeService;
    @Autowired private TravelCalculatePremiumValidator validator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationMistake> mistakes = validator.getAllMistakes(request);
        if (!mistakes.isEmpty()){
           return new TravelCalculatePremiumResponse(mistakes);
        }

        long daysBetween = dateTimeService.getDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo());
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse(request.getPersonFirstName(),request.getPersonLastName(),dateTimeService.getDateAfterFormatting(request.getAgreementDateFrom()),dateTimeService.getDateAfterFormatting(request.getAgreementDateTo()),new BigDecimal(daysBetween));
        return response;
    }
}
