package travel_insurance.core.validations.person;

import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.repositories.ClassifierValueRepository;
import travel_insurance.core.util.Placeholder;
import travel_insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public
class MedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ClassifierValueRepository classifierValueRepository;
    @Autowired private ValidationErrorFactory errorFactory;


    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (isMedicalRiskLimitLevelNotBlank(person))
                && !existInDatabase(person.getMedicalRiskLimitLevel())
                ? Optional.of(buildValidationError(person.getMedicalRiskLimitLevel()))
                : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(String medicalRiskLimitLevel) {
        Placeholder placeholder = new Placeholder("NOT_SUPPORTED_MEDICAL_RISK_LIMIT_LEVEL", medicalRiskLimitLevel);
        return errorFactory.buildError("ERROR_CODE_14", List.of(placeholder));
    }

    private boolean isMedicalRiskLimitLevelNotBlank(PersonDTO person) {
        return person.getMedicalRiskLimitLevel() != null && !person.getMedicalRiskLimitLevel().isBlank();
    }

    private boolean existInDatabase(String medicalRiscLimitLevelIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", medicalRiscLimitLevelIc).isPresent();
    }


}
