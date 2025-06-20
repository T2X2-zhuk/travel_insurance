package travel_insurance.core.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.type.YesNoConverter;


@Entity
@Table(name = "agreements_xml_export")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreementXmlExportEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agreement_uuid", nullable = false)
    private String agreementUuid;

    @Column(name = "already_exported", nullable = false)
    @Convert(converter = YesNoConverter.class)
    private Boolean alreadyExported;

}
