package travel_insurance.core.repositories.entities;


import org.springframework.data.jpa.repository.JpaRepository;
import travel_insurance.core.domain.entity.AgreementPersonEntity;
import travel_insurance.core.domain.entity.AgreementPersonRiskEntity;

import java.util.List;

public interface AgreementPersonRiskEntityRepository
        extends JpaRepository<AgreementPersonRiskEntity, Long> {
    List<AgreementPersonRiskEntity> findByAgreementPerson(AgreementPersonEntity agreementPerson);

}
