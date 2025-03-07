package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;

import java.util.Optional;
@Component
public class ValidatorAgreementDateFrom implements TravelRequestValidation{
    @Override
    public Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationMistake("Agreement Date From","Must not be empty"))
                : Optional.empty();
    }
}
