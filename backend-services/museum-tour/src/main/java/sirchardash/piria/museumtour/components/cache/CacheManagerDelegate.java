package sirchardash.piria.museumtour.components.cache;

import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sirchardash.piria.museumtour.models.weather.Weather;

@Component
public class CacheManagerDelegate {

    @Bean
    public DefaultCacheManager cacheManager() {
        return new DefaultCacheManager();
    }

    @Bean
    public Cache<String, List<Weather>> weather(DefaultCacheManager cacheManager,
                                                @Value("${museum-tour.weather.cache-response-time}") int lifespan) {
        return cacheManager.createCache(
                "weather",
                new ConfigurationBuilder()
                        .expiration().lifespan(lifespan, SECONDS)
                        .build()
        );
    }

}
