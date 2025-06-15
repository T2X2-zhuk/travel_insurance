package travel_insurance.core.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String personFirstName;
    private String personLastName;
    private String personCode;

    private BigDecimal travelCost;
    private Date personBirthDate;
    private String medicalRiskLimitLevel;
    private List<RiskDTO> risks;

}
