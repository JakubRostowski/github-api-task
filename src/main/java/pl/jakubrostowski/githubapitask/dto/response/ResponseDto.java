package pl.jakubrostowski.githubapitask.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

public record ResponseDto(String repositoryName, String ownerLogin, List<BranchResponseDto> branchInfo) {
}
