package pl.jakubrostowski.githubapitask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryDto {

    @JsonProperty("name")
    String repositoryName;

    @JsonProperty("owner")
    OwnerDto owner;

    @JsonProperty("fork")
    boolean fork;

    @JsonProperty("branches_url")
    String branchesUrl;
}
