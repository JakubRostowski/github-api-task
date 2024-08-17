package pl.jakubrostowski.githubapitask.mapper;

import lombok.NoArgsConstructor;
import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;
import pl.jakubrostowski.githubapitask.dto.response.BranchResponseDto;
import pl.jakubrostowski.githubapitask.dto.response.ResponseDto;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class GithubMapper {

    public static ResponseDto toResponseDto(RepositoryDto dto) {
        if (dto == null) {
            return null;
        }

        List<BranchResponseDto> branchInfo = new ArrayList<>();

        for (BranchDto branch : dto.getBranches()) {
            branchInfo.add(new BranchResponseDto(branch.getBranchName(), branch.getCommit().getSha()));
        }

        return new ResponseDto(dto.getRepositoryName(), dto.getOwner().getLogin(), branchInfo);
    }
}
