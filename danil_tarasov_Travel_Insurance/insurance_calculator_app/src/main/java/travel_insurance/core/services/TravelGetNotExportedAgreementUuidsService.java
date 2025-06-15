package travel_insurance.core.services;


import travel_insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import travel_insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;

public interface TravelGetNotExportedAgreementUuidsService {

    TravelGetNotExportedAgreementUuidsCoreResult getAgreementUuids(TravelGetNotExportedAgreementUuidsCoreCommand command);

}
