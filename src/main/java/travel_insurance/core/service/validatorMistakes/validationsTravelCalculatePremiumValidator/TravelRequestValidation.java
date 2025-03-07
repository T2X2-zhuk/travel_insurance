package travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator;

import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;

import java.util.Optional;

public interface TravelRequestValidation {

    Optional<ValidationMistake> execute(TravelCalculatePremiumRequest request);
}
