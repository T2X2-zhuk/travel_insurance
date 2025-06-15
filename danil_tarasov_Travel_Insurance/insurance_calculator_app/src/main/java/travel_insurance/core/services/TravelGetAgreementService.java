package travel_insurance.core.services;


import travel_insurance.core.api.command.TravelGetAgreementCoreCommand;
import travel_insurance.core.api.command.TravelGetAgreementCoreResult;

public interface TravelGetAgreementService {

    TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);

}
