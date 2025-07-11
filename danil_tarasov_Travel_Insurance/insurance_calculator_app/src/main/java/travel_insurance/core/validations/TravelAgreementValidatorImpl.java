package travel_insurance.core.validations;

import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public
class TravelAgreementValidatorImpl implements TravelAgreementValidator {

    @Autowired private TravelAgreementFieldValidator agreementFieldValidator;
    @Autowired private TravelPersonFieldValidator personFieldValidator;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> agreementErrors = agreementFieldValidator.validate(agreement);
        List<ValidationErrorDTO> personErrors = personFieldValidator.validate(agreement);
        return concatenateLists(agreementErrors, personErrors);
    }

    private List<ValidationErrorDTO> concatenateLists(List<ValidationErrorDTO> singleErrors,
                                                      List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
