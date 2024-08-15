package pl.jakubrostowski.githubapitask;

import org.junit.jupiter.api.Test;
import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.CommitDto;
import pl.jakubrostowski.githubapitask.dto.OwnerDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;
import pl.jakubrostowski.githubapitask.dto.response.ResponseDto;
import pl.jakubrostowski.githubapitask.mapper.GithubMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperTests {

    @Test
    void shouldMapToResponseDto() {
        BranchDto branchDto = new BranchDto("branch", new CommitDto("sha"));
        RepositoryDto repositoryDto = new RepositoryDto("repo", new OwnerDto("login"),
                false, new ArrayList<>(List.of(branchDto)));

        ResponseDto responseDto = GithubMapper.toResponseDto(repositoryDto);

        assertEquals("repo", responseDto.getRepositoryName());
        assertEquals("login", responseDto.getOwnerLogin());
        assertEquals("branch", responseDto.getBranchInfo().get(0).getBranchName());
        assertEquals("sha", responseDto.getBranchInfo().get(0).getLastCommitSha());
    }
}
