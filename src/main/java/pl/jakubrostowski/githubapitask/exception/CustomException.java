package pl.jakubrostowski.githubapitask.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final int statusCode;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.statusCode = status.value();
    }
}
