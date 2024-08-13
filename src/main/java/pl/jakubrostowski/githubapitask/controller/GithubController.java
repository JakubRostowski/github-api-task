package pl.jakubrostowski.githubapitask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.jakubrostowski.githubapitask.dto.response.ResponseDto;
import pl.jakubrostowski.githubapitask.service.GithubService;

import java.util.List;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping(value="/user/{username}/repositories", headers="Accept=application/json")
    public List<ResponseDto> getUserRepositories(@PathVariable String username) {
        return githubService.getUserRepositories(username);
    }
}
