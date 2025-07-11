package travel_insurance.core.repositories;

import org.springframework.cache.annotation.Cacheable;
import travel_insurance.core.domain.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    @Cacheable(cacheNames = {"classifierCache"}, key = "#p0", unless="#result == null")
    Optional<Classifier> findByTitle(String title);

}
