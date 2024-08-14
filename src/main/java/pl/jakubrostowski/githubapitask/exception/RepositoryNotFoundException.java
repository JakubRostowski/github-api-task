package pl.jakubrostowski.githubapitask.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RepositoryNotFoundException extends CustomException {

    public RepositoryNotFoundException() {
        super("No repositories found for the given username.", HttpStatus.NOT_FOUND);
    }
}
