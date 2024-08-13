package pl.jakubrostowski.githubapitask.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BranchResponseDto {

    String branchName;
    String lastCommitSha;
}
