package travel_insurance.core.repositories;

import travel_insurance.InsuranceApplication;
import travel_insurance.core.domain.Classifier;
import travel_insurance.core.repositories.ClassifierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClassifierRepositoryTest {

    @Autowired private ClassifierRepository classifierRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierRepository);
    }

    @Test
    public void shouldFindRiskTypeClassifier() {
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("RISK_TYPE");
        assertTrue(riskTypeOpt.isPresent());
        assertEquals(riskTypeOpt.get().getTitle(), "RISK_TYPE");
    }

    @Test
    public void shouldNotFindFakeClassifier() {
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("FAKE");
        assertTrue(riskTypeOpt.isEmpty());
    }

}