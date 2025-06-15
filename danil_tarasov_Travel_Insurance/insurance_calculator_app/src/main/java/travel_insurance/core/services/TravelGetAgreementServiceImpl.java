package travel_insurance.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import travel_insurance.core.api.command.TravelGetAgreementCoreCommand;
import travel_insurance.core.api.command.TravelGetAgreementCoreResult;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.validations.TravelAgreementUuidValidator;

import java.util.List;

@Component
@Transactional
class TravelGetAgreementServiceImpl implements TravelGetAgreementService {

    @Autowired private TravelAgreementUuidValidator agreementUuidValidator;
    @Autowired private AgreementDTOLoader agreementDTOLoader;

    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementUuidValidator.validate(command.getUuid());
        return errors.isEmpty()
                ? buildResponse(command.getUuid())
                : buildResponse(errors);
    }

    private TravelGetAgreementCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelGetAgreementCoreResult(errors);
    }

    private TravelGetAgreementCoreResult buildResponse(String agreementUuid) {
        TravelGetAgreementCoreResult coreResult = new TravelGetAgreementCoreResult();
        coreResult.setAgreement(agreementDTOLoader.load(agreementUuid));
        return coreResult;
    }

}
