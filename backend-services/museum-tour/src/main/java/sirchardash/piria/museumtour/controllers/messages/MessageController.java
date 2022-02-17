package sirchardash.piria.museumtour.controllers.messages;

import lombok.AllArgsConstructor;
import org.keycloak.common.VerificationException;
import org.springframework.web.bind.annotation.*;
import sirchardash.piria.museumtour.components.auth.TokenParser;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;
import sirchardash.piria.museumtour.jpa.Message;
import sirchardash.piria.museumtour.services.MessageService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class MessageController {

    private final MessageService service;
    private final TokenParser tokenParser;

    @GetMapping("/admin/messages")
    public List<Message> getAll() {
        return service.getAll();
    }

    @PostMapping("/message")
    public void save(@Valid @RequestBody NewMessageRequest newMessage,
                     @RequestHeader String authorization) throws VerificationException {
        service.save(newMessage.getMessage(), tokenParser.fromHeader(authorization));
    }

    @DeleteMapping("/admin/message/{id}")
    public void delete(@PathVariable int id) throws ServiceLogicException {
        service.delete(id);
    }

}
