package pl.jakubrostowski.githubapitask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryDto {

    @JsonProperty("name")
    String repositoryName;

    @JsonProperty("owner")
    OwnerDto owner;

    @JsonProperty("fork")
    boolean fork;

    @Setter
    List<BranchDto> branches;
}
