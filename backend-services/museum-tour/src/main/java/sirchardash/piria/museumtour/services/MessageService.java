package sirchardash.piria.museumtour.services;

import lombok.AllArgsConstructor;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;
import sirchardash.piria.museumtour.exceptions.ServiceError;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.Message;
import sirchardash.piria.museumtour.jpa.MessageRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public void save(String message,
                     AccessToken user) {
        repository.save(new Message(
                0,
                user.getOtherClaims().get("user_id").toString(),
                message,
                user.getEmail()
        ));
    }

    public List<Message> getAll() {
        return repository.findAll();
    }

    public void delete(int id) {
        Message message = repository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ServiceError.MODEL_REFERENCE_FAILS, 404));
        repository.delete(message);
    }

}
