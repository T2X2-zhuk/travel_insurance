package travel_insurance.core.validations.agreement;

import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.validations.ValidationErrorFactory;
import travel_insurance.core.api.dto.AgreementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public
class EmptyCountryValidation extends TravelAgreementFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (countryIsNullOrBlank(agreement))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean countryIsNullOrBlank(AgreementDTO agreement) {
        return agreement.getCountry() == null || agreement.getCountry().isBlank();
    }

}
