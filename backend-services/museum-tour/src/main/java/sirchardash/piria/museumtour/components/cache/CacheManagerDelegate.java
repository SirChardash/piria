package sirchardash.piria.museumtour.components.cache;

import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.geo.City;
import sirchardash.piria.museumtour.models.geo.Country;
import sirchardash.piria.museumtour.models.geo.Region;
import sirchardash.piria.museumtour.models.weather.Weather;

@Component
class CacheManagerDelegate {

    @Bean
    DefaultCacheManager cacheManager() {
        return new DefaultCacheManager();
    }

    @Bean
    Cache<String, List<Weather>> weather(DefaultCacheManager cacheManager,
                                                @Value("${museum-tour.weather.cache-response-time}") int lifespan) {
        return queryBasedCache(cacheManager, "weather", lifespan);
    }

    @Bean
    Cache<String, List<Country>> country(DefaultCacheManager cacheManager,
                                                @Value("${museum-tour.geo.cache-response-time}") int lifespan) {
        return queryBasedCache(cacheManager, "country", lifespan);
    }

    @Bean
    Cache<String, List<Region>> region(DefaultCacheManager cacheManager,
                                              @Value("${museum-tour.geo.cache-response-time}") int lifespan) {
        return queryBasedCache(cacheManager, "region", lifespan);
    }

    @Bean
    Cache<String, List<City>> city(DefaultCacheManager cacheManager,
                                          @Value("${museum-tour.geo.cache-response-time}") int lifespan) {
        return queryBasedCache(cacheManager, "city", lifespan);
    }

    private static <T> Cache<String, List<T>> queryBasedCache(DefaultCacheManager cacheManager,
                                                              String name,
                                                              int lifespan) {
        return cacheManager.createCache(
                name,
                new ConfigurationBuilder()
                        .expiration().lifespan(lifespan, SECONDS)
                        .build()
        );
    }

}
