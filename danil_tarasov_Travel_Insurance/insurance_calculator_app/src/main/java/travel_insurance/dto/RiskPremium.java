package travel_insurance.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travel_insurance.dto.util.BigDecimalSerializer;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiskPremium {

    private String riskIc;
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal premium;

}
