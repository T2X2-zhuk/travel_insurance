package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;

import java.util.Optional;

@Component
public class ValidatorPersonLastName implements TravelRequestValidation{

    @Override
    public Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationMistake("Person last name","Must not be empty"))
                : Optional.empty();
    }
}
