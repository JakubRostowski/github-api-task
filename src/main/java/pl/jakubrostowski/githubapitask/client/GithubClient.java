package pl.jakubrostowski.githubapitask.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
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

    public List<RepositoryDto> getBasicRepositoryInfo(String username) {
        RepositoryDto[] result = restClient.get().uri("/users/" + username + "/repos").retrieve().body(RepositoryDto[].class);
        return Arrays.stream(result)
                .filter(repo -> !repo.isFork())
                .toList();
    }

}
