package pl.jakubrostowski.githubapitask.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubClient {

    private final RestClient restClient;

    public GithubClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("https://api.github.com")
                .defaultHeader("Accept", "application/vnd.github.v3+json")
                .build();
    }

    public List<RepositoryDto> getRepositoryListByUser(String username) {
        RepositoryDto[] result = restClient
                .get()
                .uri("/users/" + username + "/repos")
                .retrieve()
                .body(RepositoryDto[].class);

        return Arrays.stream(result)
                .toList();
    }

    public List<BranchDto> getBranchInfo(String ownerName, String repoName) {
        BranchDto[] result = restClient
                .get()
                .uri("/repos/" + ownerName + "/" + repoName + "/branches")
                .retrieve()
                .body(BranchDto[].class);

        return Arrays.stream(result)
                .toList();
    }

}
