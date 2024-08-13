package pl.jakubrostowski.githubapitask.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GithubBranchInfoDto {

    String branchName;
    String lastCommitSha;
}
