package travel_insurance.core.repositories.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import travel_insurance.core.domain.entity.AgreementEntity;
import travel_insurance.core.domain.entity.AgreementPersonEntity;

import java.util.List;

public interface AgreementPersonEntityRepository
        extends JpaRepository<AgreementPersonEntity, Long> {
    List<AgreementPersonEntity> findByAgreement(AgreementEntity agreement);

}
