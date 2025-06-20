package travel_insurance.core.validations.agreement;

import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.validations.ValidationErrorFactory;
import travel_insurance.core.api.dto.AgreementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public
class EmptySelectedRisksValidation extends TravelAgreementFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (agreement.getSelectedRisks() == null || agreement.getSelectedRisks().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }

}
