package core.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalGenerationAcknowledgment {

    private String agreementUuid;
    private String proposalFilePath;

}
