package travel_insurance.core.messagebroker.proposalack;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalGenerationAck {

    private String agreementUuid;
    private String proposalFilePath;

}
