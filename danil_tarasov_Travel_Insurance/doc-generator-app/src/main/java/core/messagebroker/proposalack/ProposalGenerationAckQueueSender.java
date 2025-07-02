package core.messagebroker.proposalack;


import core.dto.AgreementDTO;

public interface ProposalGenerationAckQueueSender {

    void send(AgreementDTO agreement, String proposalFilePath);

}
