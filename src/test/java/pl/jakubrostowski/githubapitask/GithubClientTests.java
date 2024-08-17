package pl.jakubrostowski.githubapitask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;
import pl.jakubrostowski.githubapitask.client.GithubClient;
import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.CommitDto;
import pl.jakubrostowski.githubapitask.dto.OwnerDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;
import pl.jakubrostowski.githubapitask.exception.RepositoryNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(GithubClient.class)
class GithubClientTests {

    @Autowired
    MockRestServiceServer serviceServer;

    @Autowired
    GithubClient githubClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindRepositories() throws JsonProcessingException {
        List<RepositoryDto> data = List.of(
                new RepositoryDto("first", new OwnerDto("one"), true, List.of(new BranchDto())),
                new RepositoryDto("second", new OwnerDto("two"), false, List.of(new BranchDto()))
        );

        serviceServer.expect(requestTo("https://api.github.com/users/user/repos"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), APPLICATION_JSON));

        List<RepositoryDto> repos = githubClient.getRepositoryListByUser("user");
        assertEquals(2, repos.size());
        assertEquals("first", repos.get(0).getRepositoryName());
        assertEquals("one", repos.get(0).getOwner().getLogin());
        assertTrue(repos.get(0).isFork());
    }

    @Test
    void shouldGetBranchInfo() throws JsonProcessingException {
        List<BranchDto> data = List.of(
                new BranchDto("first", new CommitDto("one")),
                new BranchDto("second", new CommitDto("two"))
        );

        serviceServer.expect(requestTo("https://api.github.com/repos/user/repo/branches"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), APPLICATION_JSON));

        List<BranchDto> branches = githubClient.getBranchInfo("user", "repo");
        assertEquals(2, branches.size());
        assertEquals("first", branches.get(0).getBranchName());
        assertEquals("one", branches.get(0).getCommit().getSha());
    }

    @Test
    void shouldThrowException() {
        serviceServer.expect(requestTo("https://api.github.com/users/non-existing-user/repos"))
                .andRespond(withStatus(NOT_FOUND).contentType(APPLICATION_JSON));

        RepositoryNotFoundException ex = assertThrows(RepositoryNotFoundException.class, () ->
                githubClient.getRepositoryListByUser("non-existing-user"));
        assertEquals(NOT_FOUND.value(), ex.getStatusCode());
        assertEquals("No repositories found for the given username.", ex.getMessage());
    }
}
