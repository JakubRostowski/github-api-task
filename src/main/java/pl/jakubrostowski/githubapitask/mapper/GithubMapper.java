package pl.jakubrostowski.githubapitask.mapper;

import pl.jakubrostowski.githubapitask.dto.BranchDto;
import pl.jakubrostowski.githubapitask.dto.GithubBranchInfoDto;
import pl.jakubrostowski.githubapitask.dto.GithubResponseDto;
import pl.jakubrostowski.githubapitask.dto.RepositoryDto;

import java.util.ArrayList;
import java.util.List;

public final class GithubMapper {

    public static GithubResponseDto toResponseDto(RepositoryDto dto) {
        if (dto == null) {
            return null;
        }

        List<GithubBranchInfoDto> branchInfo = new ArrayList<>();

        for (BranchDto branch : dto.getBranch()) {
            branchInfo.add(
                    GithubBranchInfoDto.builder()
                    .branchName(branch.getBranchName())
                    .lastCommitSha(branch.getCommit().getSha())
                    .build()
            );
        }

        return GithubResponseDto.builder()
                .repositoryName(dto.getRepositoryName())
                .ownerLogin(dto.getOwner().getLogin())
                .branchInfo(branchInfo)
                .build();
    }
}
