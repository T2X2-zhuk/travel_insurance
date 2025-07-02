package travel_insurance.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import travel_insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import travel_insurance.core.api.command.TravelCalculatePremiumCoreResult;
import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.api.dto.RiskDTO;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.domain.entity.AgreementEntity;

import travel_insurance.core.messagebroker.proposal.ProposalGeneratorQueueSender;
import travel_insurance.core.validations.TravelAgreementValidator;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Component
@Transactional
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired private TravelAgreementValidator agreementValidator;
    @Autowired private AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    @Autowired private AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;

    @Autowired private ProposalGeneratorQueueSender proposalGeneratorQueueSender;
    @Autowired private AgreementEntityFactory agreementEntityFactory;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        if (errors.isEmpty()) {
            calculatePremium(command.getAgreement());
            AgreementEntity agreement = agreementEntityFactory.createAgreementEntity(command.getAgreement());
            command.getAgreement().setUuid(agreement.getUuid());
            proposalGeneratorQueueSender.send(command.getAgreement());
            return buildResponse(command.getAgreement());
        } else {
            return buildResponse(errors);
        }
    }

    private void calculatePremium(AgreementDTO agreement) {
        agreementPersonsPremiumCalculator.calculateRiskPremiums(agreement);
        agreement.setAgreementPremium(agreementTotalPremiumCalculator.calculate(agreement));
    }

    private TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(AgreementDTO agreement) {
        return new TravelCalculatePremiumCoreResult(null, agreement);
    }

}
