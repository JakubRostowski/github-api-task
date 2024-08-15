package pl.jakubrostowski.githubapitask;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jakubrostowski.githubapitask.client.GithubClient;
import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.CommitDto;
import pl.jakubrostowski.githubapitask.dto.OwnerDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;
import pl.jakubrostowski.githubapitask.dto.response.ResponseDto;
import pl.jakubrostowski.githubapitask.service.GithubService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GithubServiceTests {

    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private GithubService githubService;

    @Test
    void shouldFilteredOutForkedRepositories() {
        MockitoAnnotations.openMocks(this);

        List<RepositoryDto> repos = List.of(
                new RepositoryDto("first", new OwnerDto("user"), false, null),
                new RepositoryDto("second", new OwnerDto("user"), true, null)
        );

        List<BranchDto> branches = List.of(
                new BranchDto("branch", new CommitDto("sha")));

        when(githubClient.getRepositoryListByUser("user")).thenReturn(repos);
        when(githubClient.getBranchInfo("user", "first")).thenReturn(branches);
        when(githubClient.getBranchInfo("user", "second")).thenReturn(branches);

        List<ResponseDto> result = githubService.getUserRepositories("user");
        assertEquals(1, result.size());
    }
}
