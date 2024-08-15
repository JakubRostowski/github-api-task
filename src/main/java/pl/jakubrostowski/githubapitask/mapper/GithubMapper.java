package pl.jakubrostowski.githubapitask.mapper;

import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;
import pl.jakubrostowski.githubapitask.dto.response.BranchResponseDto;
import pl.jakubrostowski.githubapitask.dto.response.ResponseDto;

import java.util.ArrayList;
import java.util.List;

public final class GithubMapper {

    public static ResponseDto toResponseDto(RepositoryDto dto) {
        if (dto == null) {
            return null;
        }

        List<BranchResponseDto> branchInfo = new ArrayList<>();

        for (BranchDto branch : dto.getBranches()) {
            branchInfo.add(
                    BranchResponseDto.builder()
                            .branchName(branch.getBranchName())
                            .lastCommitSha(branch.getCommit().getSha())
                            .build()
            );
        }

        return ResponseDto.builder()
                .repositoryName(dto.getRepositoryName())
                .ownerLogin(dto.getOwner().getLogin())
                .branchInfo(branchInfo)
                .build();
    }
}
