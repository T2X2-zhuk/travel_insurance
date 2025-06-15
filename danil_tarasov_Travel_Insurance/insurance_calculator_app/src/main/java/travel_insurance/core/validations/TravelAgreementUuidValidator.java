package travel_insurance.core.validations;


import travel_insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementUuidValidator {

    List<ValidationErrorDTO> validate(String uuid);

}
