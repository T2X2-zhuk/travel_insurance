package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.DateTimeService;

import java.util.Date;
import java.util.Optional;
@Component
public class ValidateDateToInFuture implements TravelRequestValidation{
    @Override
    public Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request) {
        Date dateTo = new DateTimeService().getDateAfterFormatting(request.getAgreementDateTo());
        Date currentDateTime = new DateTimeService().getCurrentDateTime();
        return (dateTo != null && dateTo.before(currentDateTime))
                ? Optional.of(new ValidationMistake("Agreement Date To", "Must be in the future!"))
                : Optional.empty();
    }
}
