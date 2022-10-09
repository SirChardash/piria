package sirchardash.piria.museumtour.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sirchardash.piria.museumtour.exceptions.ServiceLogicException;

import java.util.Map;

//@ControllerAdvice
public class ServiceLogicExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceLogicException.class)
    protected ResponseEntity<Object> handle(ServiceLogicException e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Map.of("errorCode", e.getServiceError().getCode()),
                new HttpHeaders(),
                HttpStatus.valueOf(e.getStatusCode()),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOther(Exception e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Map.of("error", e.getMessage()),
                new HttpHeaders(),
                HttpStatus.valueOf(400),
                request
        );
    }

}
