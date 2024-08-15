package pl.jakubrostowski.githubapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubrostowski.githubapitask.client.GithubClient;
import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;
import pl.jakubrostowski.githubapitask.dto.response.ResponseDto;
import pl.jakubrostowski.githubapitask.mapper.GithubMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;

    public List<ResponseDto> getUserRepositories(String username) {
        List<RepositoryDto> repositories = githubClient.getRepositoryListByUser(username);

        List<RepositoryDto> filteredRepositories = repositories.stream()
                .filter(repo -> !repo.isFork())
                .toList();

        for (RepositoryDto dto : filteredRepositories) {
            List<BranchDto> branches = githubClient.getBranchInfo(dto.getOwner().getLogin(), dto.getRepositoryName());
            dto.setBranches(branches);
        }

        return repositories.stream()
                .map(GithubMapper::toResponseDto)
                .toList();
    }
}
