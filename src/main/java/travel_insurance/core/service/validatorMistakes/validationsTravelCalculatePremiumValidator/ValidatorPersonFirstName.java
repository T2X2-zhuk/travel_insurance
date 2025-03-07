package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;

import java.util.Optional;
@Component
public class ValidatorPersonFirstName implements TravelRequestValidation{

    @Override
    public Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationMistake("Person first name","Must not be empty"))
                : Optional.empty();
    }
}
