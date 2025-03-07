package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.DateTimeService;

import java.util.Date;
import java.util.Optional;
@Component
public class ValidateDateFromInFuture implements TravelRequestValidation{
    @Override
    public Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = new DateTimeService().getDateAfterFormatting(request.getAgreementDateFrom());
        Date currentDateTime = new DateTimeService().getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentDateTime))
                ? Optional.of(new ValidationMistake("Agreement Date From", "Must be in the future!"))
                : Optional.empty();
    }
}
