package travel_insurance.core.repositories;

import org.springframework.cache.annotation.Cacheable;
import travel_insurance.core.domain.TMCountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TMCountryDefaultDayRateRepository
        extends JpaRepository<TMCountryDefaultDayRate, Long> {

    @Cacheable(cacheNames = {"tmCountryDefaultDayRateCache"}, key = "#p0", unless= "#result == null")
    Optional<TMCountryDefaultDayRate> findByCountryIc(String countryIc);

}
