package pl.jakubrostowski.githubapitask.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GithubResponseDto {

    String repositoryName;
    String ownerLogin;
    List<GithubBranchInfoDto> branchInfo;
}
