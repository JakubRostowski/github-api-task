package pl.jakubrostowski.githubapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubrostowski.githubapitask.client.GithubClient;
import pl.jakubrostowski.githubapitask.dto.GithubResponseDto;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;

    public GithubResponseDto getRepositoriesForGivenUser(String username) {

    }
}
