package ie.atu.bam;

import org.hibernate.ObjectNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> showErrors(MethodArgumentNotValidException ex){

        Map<String,String> errorList = new HashMap<>();
        for(FieldError error: ex.getBindingResult().getFieldErrors()){
            String err_name = error.getField();
            String err_message = error.getDefaultMessage();
            errorList.put(err_name,err_message);
        }
        return ResponseEntity.status(400).body(errorList);

    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleFeignException(ResponseStatusException er) {
        return ResponseEntity.status(er.getStatusCode()).body("Feign Error: " + er.getReason());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<List<Object>> handleLoginError(ObjectNotFoundException eo) {
        System.err.println("Error: " + eo.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
    }
}
