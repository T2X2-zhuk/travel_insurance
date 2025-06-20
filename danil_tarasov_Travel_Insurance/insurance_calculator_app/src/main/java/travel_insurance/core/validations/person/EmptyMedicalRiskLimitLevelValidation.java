package travel_insurance.core.validations.person;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public
class EmptyMedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (isMedicalRiskLimitLevelEnabled()
                && containsTravelMedical(agreement)
                && isMedicalRiskLimitLevelIsNullOrBlank(person))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

    private boolean isMedicalRiskLimitLevelEnabled() {
        return medicalRiskLimitLevelEnabled;
    }

    private boolean containsTravelMedical(AgreementDTO agreement) {
        return agreement.getSelectedRisks() != null
                && agreement.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean isMedicalRiskLimitLevelIsNullOrBlank(PersonDTO person) {
        return person.getMedicalRiskLimitLevel() == null || person.getMedicalRiskLimitLevel().isBlank();
    }

}
