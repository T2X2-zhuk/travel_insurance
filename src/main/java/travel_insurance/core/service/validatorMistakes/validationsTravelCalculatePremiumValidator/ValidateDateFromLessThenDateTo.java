package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.DateTimeService;

import java.util.Date;
import java.util.Optional;
@Component
public class ValidateDateFromLessThenDateTo implements TravelRequestValidation{
    @Override
    public Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = new DateTimeService().getDateAfterFormatting(request.getAgreementDateFrom());
        Date dateTo = new DateTimeService().getDateAfterFormatting(request.getAgreementDateTo());
        return (dateFrom.equals(dateTo) || dateFrom.after(dateTo))
                ? Optional.of(new ValidationMistake("Agreement Date From", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }
}
