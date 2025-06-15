package travel_insurance.core.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import travel_insurance.core.api.command.TravelGetAllAgreementUuidsCoreCommand;
import travel_insurance.core.api.command.TravelGetAllAgreementUuidsCoreResult;
import travel_insurance.core.repositories.entities.AgreementEntityRepository;

import java.util.List;

@Component
@Transactional
class TravelGetAllAgreementUuidsServiceImpl
        implements TravelGetAllAgreementUuidsService {

    @Autowired
    private AgreementEntityRepository agreementRepository;

    @Override
    public TravelGetAllAgreementUuidsCoreResult getAgreement(TravelGetAllAgreementUuidsCoreCommand command) {
        List<String> agreementUuids = agreementRepository.getAllAgreementUuids();
        return new TravelGetAllAgreementUuidsCoreResult(null, agreementUuids);
    }

}
