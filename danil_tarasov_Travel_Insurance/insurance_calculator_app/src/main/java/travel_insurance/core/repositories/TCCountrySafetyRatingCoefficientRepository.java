package travel_insurance.core.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import travel_insurance.core.domain.TCCountrySafetyRatingCoefficient;

import java.util.Optional;

public interface TCCountrySafetyRatingCoefficientRepository
        extends JpaRepository<TCCountrySafetyRatingCoefficient, Long> {

    @Cacheable(cacheNames = {"tcCountrySafetyRatingCache"}, key = "#p0", unless="#result == null")
    Optional<TCCountrySafetyRatingCoefficient> findByCountryIc(String countryIc);

}
