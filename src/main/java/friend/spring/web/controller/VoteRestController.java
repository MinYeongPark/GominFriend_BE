package friend.spring.web.controller;

import friend.spring.apiPayload.ApiResponse;
import friend.spring.converter.VoteConverter;
import friend.spring.domain.General_vote;
import friend.spring.service.VoteService;
import friend.spring.web.dto.VoteRequestDTO;
import friend.spring.web.dto.VoteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/posts")
public class VoteRestController {
    private final VoteService voteService;
    @PostMapping("/{post-id}/{user-id}/normalVote")
    @Operation(summary = "일반 투표 API", description = "임시로 user-id 입력")
    public ApiResponse<VoteResponseDTO.GeneralVoteResponseDTO> join(@RequestBody @Valid VoteRequestDTO.GeneralVoteRequestDTO request,
                                                                    @PathVariable(name="user-id")Long UserId){
        General_vote generalVote= voteService.castVote(request,UserId);
        return ApiResponse.onSuccess(VoteConverter.toAddGeneralVoteResultDTO(generalVote));
    }
}

