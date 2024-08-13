package pl.jakubrostowski.githubapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubrostowski.githubapitask.client.GithubClient;
import pl.jakubrostowski.githubapitask.dto.GithubResponseDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;

    public GithubResponseDto getUserRepositories(String username) {
        List<RepositoryDto> repositories =  githubClient.getRepositoryInfo(username);

        for (RepositoryDto dto : repositories) {
            githubClient.getBranchInfo(dto.getOwner().getLogin(), dto.getRepositoryName());
        }

        return null;
    }
}
