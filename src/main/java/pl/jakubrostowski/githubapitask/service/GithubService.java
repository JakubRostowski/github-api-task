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
        List<RepositoryDto> repos = githubClient.getRepositoryListByUser(username)
                .stream()
                .filter(repo -> !repo.isFork())
                .toList();

        repos.forEach(repo -> {
            List<BranchDto> branches = githubClient.getBranchInfo(repo.getOwner().getLogin(), repo.getRepositoryName());
            repo.setBranches(branches);
        });

        return repos.stream()
                .map(GithubMapper::toResponseDto)
                .toList();
    }
}
