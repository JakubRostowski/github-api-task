package pl.jakubrostowski.githubapitask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchDto {

    @JsonProperty("name")
    String branchName;

    @JsonProperty("commit")
    CommitDto commit;
}
