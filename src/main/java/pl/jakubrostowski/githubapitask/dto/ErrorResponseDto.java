package pl.jakubrostowski.githubapitask.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponseDto {

    private final int status;
    private final String message;
}
