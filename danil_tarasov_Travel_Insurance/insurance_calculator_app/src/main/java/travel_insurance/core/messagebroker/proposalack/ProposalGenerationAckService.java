package travel_insurance.core.messagebroker.proposalack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import travel_insurance.core.domain.entity.AgreementProposalAckEntity;
import travel_insurance.core.repositories.entities.AgreementProposalAckEntityRepository;

@Component
@Transactional
class
ProposalGenerationAckService {

    private static final Logger logger = LoggerFactory.getLogger(ProposalGenerationAckService.class);

    @Autowired private AgreementProposalAckEntityRepository proposalAckEntityRepository;

    public void process(ProposalGenerationAck proposalGenerationAck) {
        logger.info("Start to process proposal ack: " + proposalGenerationAck.getAgreementUuid());

        AgreementProposalAckEntity ack = new AgreementProposalAckEntity();
        ack.setAgreementUuid(proposalGenerationAck.getAgreementUuid());
        ack.setAlreadyGenerated(true);
        ack.setProposalFilePath(proposalGenerationAck.getProposalFilePath());

        proposalAckEntityRepository.save(ack);

        logger.info("Finish to process proposal ack: " + proposalGenerationAck.getAgreementUuid());
    }

}
