package travel_insurance.core.repositories;

import org.springframework.cache.annotation.Cacheable;
import travel_insurance.core.domain.TMAgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TMAgeCoefficientRepository
        extends JpaRepository<TMAgeCoefficient, Long> {

    @Cacheable(cacheNames = {"tmAgeCoefficientCache"}, key = "#p0", unless="#result == null")
    @Query("SELECT tm from TMAgeCoefficient tm " +
            "where tm.ageFrom <= :age " +
            "and tm.ageTo >= :age")
    Optional<TMAgeCoefficient> findCoefficient(@Param("age") Integer age);

}
