package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;

import java.util.Optional;
@Component
public class ValidatorAgreementDateTo implements TravelRequestValidation{
    @Override
    public Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationMistake("Agreement Date To","Must not be empty"))
                : Optional.empty();
    }
}
