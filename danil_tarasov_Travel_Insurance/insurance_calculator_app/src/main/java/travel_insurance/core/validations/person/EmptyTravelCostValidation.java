package travel_insurance.core.validations.person;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.validations.ValidationErrorFactory;

import java.util.Optional;

@Component
class EmptyTravelCostValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (containsTravelCancellation(agreement)
                    && isTravelCostIsNull(person))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_19"))
                : Optional.empty();
    }

    private boolean containsTravelCancellation(AgreementDTO agreement) {
        return agreement.getSelectedRisks() != null
                && agreement.getSelectedRisks().contains("TRAVEL_CANCELLATION");
    }

    private boolean isTravelCostIsNull(PersonDTO person) {
        return person.getTravelCost() == null;
    }

}
