package travel_insurance.core.messagebroker.proposal;


import travel_insurance.core.api.dto.AgreementDTO;

public interface ProposalGeneratorQueueSender {

    void send(AgreementDTO agreement);

}
