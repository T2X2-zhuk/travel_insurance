package travel_insurance.core.services;


import travel_insurance.core.api.command.TravelGetAllAgreementUuidsCoreCommand;
import travel_insurance.core.api.command.TravelGetAllAgreementUuidsCoreResult;

public interface TravelGetAllAgreementUuidsService {

    TravelGetAllAgreementUuidsCoreResult getAgreement(TravelGetAllAgreementUuidsCoreCommand command);

}
