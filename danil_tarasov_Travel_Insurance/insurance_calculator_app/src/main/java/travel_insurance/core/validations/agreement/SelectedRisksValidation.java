package travel_insurance.core.validations.agreement;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.repositories.ClassifierValueRepository;
import travel_insurance.core.util.Placeholder;
import travel_insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public
class SelectedRisksValidation extends TravelAgreementFieldValidationImpl {

    @Autowired private ClassifierValueRepository classifierValueRepository;
    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        return agreement.getSelectedRisks() != null
                ? validateSelectedRisks(agreement)
                : List.of();
    }

    private List<ValidationErrorDTO> validateSelectedRisks(AgreementDTO agreement) {
        return agreement.getSelectedRisks().stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationErrorDTO> validateRiskIc(String riskIc) {
        return !existInDatabase(riskIc)
                ? Optional.of(buildValidationError(riskIc))
                : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(String riskIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return errorFactory.buildError("ERROR_CODE_9", List.of(placeholder));
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

}
