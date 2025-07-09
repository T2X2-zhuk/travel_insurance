package blacklist.core.validations;

import blacklist.core.api.BlackListedPersonDTO;
import blacklist.core.api.ValidationErrorDTO;

import java.util.List;

public interface BlackListedPersonValidator {

    List<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO);

}
