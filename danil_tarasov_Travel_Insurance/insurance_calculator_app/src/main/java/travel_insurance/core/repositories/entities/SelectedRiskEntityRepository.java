package travel_insurance.core.repositories.entities;


import org.springframework.data.jpa.repository.JpaRepository;
import travel_insurance.core.domain.entity.AgreementEntity;
import travel_insurance.core.domain.entity.SelectedRiskEntity;

import java.util.List;

public interface SelectedRiskEntityRepository extends JpaRepository<SelectedRiskEntity, Long> {

    List<SelectedRiskEntity> findByAgreement(AgreementEntity agreement);

}
