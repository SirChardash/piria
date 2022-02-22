package sirchardash.piria.museumtour.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirchardash.piria.museumtour.components.geo.restcountries.RestCountriesApi;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddMuseumService {

    private final MuseumRepository museumRepository;
    private final MasterMuseumRepository masterMuseumRepository;
    private final LanguageRepository languageRepository;
    private final RestCountriesApi restCountriesApi;
    private List<String> supportedLanguages;

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

        museumLocalizations.forEach(museum -> museum.setCountryCode(
                restCountriesApi.toCountryCode(museum.getCountry())
        ));
        MasterMuseum masterMuseum = masterMuseumRepository.save(new MasterMuseum());
        museumLocalizations.forEach(museum -> museum.setMasterId(masterMuseum.getId()));
        museumRepository.saveAll(museumLocalizations);
    }


}
