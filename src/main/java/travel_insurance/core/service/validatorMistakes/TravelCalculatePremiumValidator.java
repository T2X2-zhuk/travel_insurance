package travel_insurance.core.service.validatorMistakes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;
import travel_insurance.core.service.DateTimeService;
import travel_insurance.core.service.validatorMistakes.validationsTravelCalculatePremiumValidator.TravelRequestValidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TravelCalculatePremiumValidator {

    @Autowired
    private List<TravelRequestValidation> travelValidations;

    public List<ValidationMistake> validate(TravelCalculatePremiumRequest request) {
        return travelValidations.stream()
                .map(validation -> validation.execute(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
