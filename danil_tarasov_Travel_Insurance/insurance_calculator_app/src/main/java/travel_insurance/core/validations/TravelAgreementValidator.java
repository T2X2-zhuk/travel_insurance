package travel_insurance.core.validations;

import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.api.dto.AgreementDTO;

import java.util.List;

public interface TravelAgreementValidator {

    List<ValidationErrorDTO> validate(AgreementDTO agreement);

}
