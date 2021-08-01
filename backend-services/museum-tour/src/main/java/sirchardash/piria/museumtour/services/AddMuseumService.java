package sirchardash.piria.museumtour.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.Language;
import sirchardash.piria.museumtour.jpa.LanguageRepository;
import sirchardash.piria.museumtour.jpa.MasterMuseum;
import sirchardash.piria.museumtour.jpa.MasterMuseumRepository;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.jpa.MuseumRepository;

@Service
public class AddMuseumService {

    private final MuseumRepository museumRepository;
    private final MasterMuseumRepository masterMuseumRepository;
    private final LanguageRepository languageRepository;
    private List<String> supportedLanguages = List.of();

    @Autowired
    public AddMuseumService(MuseumRepository museumRepository,
                            MasterMuseumRepository masterMuseumRepository,
                            LanguageRepository languageRepository) {
        this.museumRepository = museumRepository;
        this.masterMuseumRepository = masterMuseumRepository;
        this.languageRepository = languageRepository;
    }

    @PostConstruct
    void loadSupportedLanguages() {
        supportedLanguages = languageRepository.findAll().stream()
                .map(Language::getCode)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addNewMuseum(Collection<Museum> museumLocalizations) throws ServiceLogicException {
        Set<String> languages = museumLocalizations.stream().map(Museum::getLanguage).collect(Collectors.toSet());

        if (languages.size() != museumLocalizations.size()) {
            throw new ServiceLogicException(ServiceError.DUPLICATE_ENTRIES, 400);
        } else if (!supportedLanguages.containsAll(languages)) {
            throw new ServiceLogicException(ServiceError.UNSUPPORTED_LANGUAGE, 400);
        }

        MasterMuseum masterMuseum = masterMuseumRepository.save(new MasterMuseum());
        museumLocalizations.forEach(museum -> museum.setMasterId(masterMuseum.getId()));
        museumRepository.saveAll(museumLocalizations);
    }


}
