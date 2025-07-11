package travel_insurance.dto.internal;

import org.springframework.stereotype.Component;
import travel_insurance.core.api.command.TravelGetAgreementCoreCommand;
import travel_insurance.core.api.command.TravelGetAgreementCoreResult;
import travel_insurance.core.api.dto.AgreementDTO;
import travel_insurance.core.api.dto.PersonDTO;
import travel_insurance.core.api.dto.RiskDTO;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.dto.RiskPremium;
import travel_insurance.dto.ValidationError;
import travel_insurance.dto.v2.PersonResponseDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAgreementDtoConverter {

    public TravelGetAgreementCoreCommand buildCoreCommand(String uuid) {
        return new TravelGetAgreementCoreCommand(uuid);
    }

    public TravelGetAgreementResponse buildResponse(TravelGetAgreementCoreResult coreResult) {
        return coreResult.hasErrors()
                ? buildResponseWithErrors(coreResult.getErrors())
                : buildSuccessfulResponse(coreResult);
    }

    private TravelGetAgreementResponse buildResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrors(coreErrors);
        return new TravelGetAgreementResponse(errors);
    }

    private List<ValidationError> transformValidationErrors(List<ValidationErrorDTO> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
                .collect(Collectors.toList());
    }

    private TravelGetAgreementResponse buildSuccessfulResponse(TravelGetAgreementCoreResult coreResult) {
        AgreementDTO agreement = coreResult.getAgreement();
        TravelGetAgreementResponse response = new TravelGetAgreementResponse();
        response.setUuid(agreement.getUuid());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());

        List<PersonResponseDTO> personResponseDTOS = agreement.getPersons().stream()
                .map(this::buildPersonFromResponse)
                .toList();
        response.setPersons(personResponseDTOS);

        return response;
    }

    private PersonResponseDTO buildPersonFromResponse(PersonDTO personDTO) {
        PersonResponseDTO person = new PersonResponseDTO();
        person.setPersonFirstName(personDTO.getPersonFirstName());
        person.setPersonLastName(personDTO.getPersonLastName());
        person.setPersonCode(personDTO.getPersonCode());
        person.setPersonBirthDate(personDTO.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        person.setTravelCost(personDTO.getTravelCost());

        person.setPersonPremium(personDTO.getRisks().stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        person.setPersonRisks(personDTO.getRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList()));

        return person;
    }

}
