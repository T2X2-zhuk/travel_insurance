package travel_insurance.core.repositories.entities;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import travel_insurance.core.domain.entity.AgreementEntity;

import java.util.List;
import java.util.Optional;

public interface AgreementEntityRepository extends JpaRepository<AgreementEntity, Long> {

    Optional<AgreementEntity> findByUuid(String uuid);

    @Query("select agr.uuid from AgreementEntity agr")
    List<String> getAllAgreementUuids();

    @Query(value = "SELECT agr.uuid " +
            "FROM agreements agr " +
            "WHERE agr.uuid NOT IN (SELECT agreement_uuid FROM agreements_xml_export)", nativeQuery = true)
    List<String> getNotExportedAgreementUuids();

}
