package sirchardash.piria.museumtour.services;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.components.museum.MuseumRefresh;

@EnableScheduling
@Service("dataRefresh")
public class DataRefreshService {

    private final List<MuseumRefresh> museumRefresh;

    @Autowired
    public DataRefreshService(List<MuseumRefresh> museumRefresh) {
        this.museumRefresh = museumRefresh;
    }

    @PostConstruct
    @Scheduled(cron = "${museum-tour.museum.refresh-cron}")
    public void museumRefresh() {
        museumRefresh.forEach(MuseumRefresh::refresh);
    }

}
