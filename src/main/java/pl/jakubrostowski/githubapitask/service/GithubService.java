package pl.jakubrostowski.githubapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubrostowski.githubapitask.client.GithubClient;
import pl.jakubrostowski.githubapitask.dto.GithubResponseDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;
import pl.jakubrostowski.githubapitask.mapper.GithubMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;

    public List<GithubResponseDto> getUserRepositories(String username) {
        List<RepositoryDto> repositories =  githubClient.getRepositoryInfo(username);

        for (RepositoryDto dto : repositories) {
            dto.setBranch(githubClient.getBranchInfo(dto.getOwner().getLogin(), dto.getRepositoryName()));
        }

        return repositories.stream()
                .map(GithubMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
