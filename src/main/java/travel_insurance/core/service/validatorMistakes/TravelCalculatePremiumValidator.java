package travel_insurance.core.service.validatorMistakes;

import org.springframework.stereotype.Component;
import travel_insurance.core.request.TravelCalculatePremiumRequest;
import travel_insurance.core.response.ValidationMistake;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class TravelCalculatePremiumValidator {

    public List<ValidationMistake> getAllMistakes(TravelCalculatePremiumRequest request){
        List<ValidationMistake> mistakes = new ArrayList<>();
        validatorPersonFirstName(request).ifPresent(mistakes ::add);
        validatorPersonLastName(request).ifPresent(mistakes ::add);
        validatorAgreementDateFrom(request).ifPresent(mistakes ::add);
        validatorAgreementDateTo(request).ifPresent(mistakes ::add);
        return mistakes;
    }

    private Optional<ValidationMistake> validatorPersonFirstName(TravelCalculatePremiumRequest request){
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
        ? Optional.of(new ValidationMistake("Person first name","Must not be empty"))
        : Optional.empty();
    }

    private Optional<ValidationMistake> validatorPersonLastName(TravelCalculatePremiumRequest request){
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationMistake("Person last name","Must not be empty"))
                : Optional.empty();
    }

    private Optional<ValidationMistake> validatorAgreementDateFrom(TravelCalculatePremiumRequest request){
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationMistake("Agreement Date From","Must not be empty"))
                : Optional.empty();
    }

    private Optional<ValidationMistake> validatorAgreementDateTo(TravelCalculatePremiumRequest request){
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationMistake("Agreement Date To","Must not be empty"))
                : Optional.empty();
    }
}
