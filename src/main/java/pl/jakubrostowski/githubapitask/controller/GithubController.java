package pl.jakubrostowski.githubapitask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubrostowski.githubapitask.dto.GithubRequestDto;
import pl.jakubrostowski.githubapitask.dto.GithubResponseDto;
import pl.jakubrostowski.githubapitask.service.GithubService;

@RestController
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping
    public GithubResponseDto getUserRepositories(@RequestBody GithubRequestDto dto) {

    }
}
