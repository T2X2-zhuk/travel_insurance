package travel_insurance.core.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.repositories.entities.AgreementEntityRepository;
import travel_insurance.core.util.Placeholder;

import java.util.ArrayList;
import java.util.List;

@Component
class TravelAgreementUuidValidatorImpl implements TravelAgreementUuidValidator {

    @Autowired private ValidationErrorFactory errorFactory;
    @Autowired private AgreementEntityRepository agreementEntityRepository;

    public List<ValidationErrorDTO> validate(String uuid) {
        List<ValidationErrorDTO> errors = new ArrayList<>();
        if (uuid == null) {
            errors.add(errorFactory.buildError("ERROR_CODE_17"));
        } else {
            if (agreementEntityRepository.findByUuid(uuid).isEmpty()) {
                Placeholder placeholder = new Placeholder("AGREEMENT_UUID", uuid);
                errors.add(errorFactory.buildError("ERROR_CODE_18", List.of(placeholder)));
            }
        }
        return errors;
    }

}
