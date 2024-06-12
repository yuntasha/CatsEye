package capstone.replyRecoommend.pet.controller;


import capstone.replyRecoommend.config.S3Uploader;
import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.global.response.SuccessResponse;
import capstone.replyRecoommend.pet.dto.PetDtoReq;
import capstone.replyRecoommend.pet.dto.PetDtoRes;
import capstone.replyRecoommend.pet.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pet")
public class PetController {

    private final PetService petService;

    /**
     * 작성자 : 정주현
     * 내 반려동물 등록(추가)하기
     */
    @PostMapping("/enroll")
    public SuccessResponse<PetDtoRes.enrollPet> toPet(@RequestPart PetDtoReq.toPetReq Dto,
                                                      @RequestPart MultipartFile Image,
                                                      @AuthenticationPrincipal Long userId){
        return SuccessResponse.success(petService.toPet(Dto,userId,Image));

    }
    /**
     * 작성자 : 정주현
     * 내 반려동물 목록 조회
     */
    @GetMapping("/search")
    public SuccessResponse<List<PetDtoRes.searchPet>> searchPet(@AuthenticationPrincipal Long userId){
        List<PetDtoRes.searchPet> searchPetList = petService.searchPet(userId);

        return SuccessResponse.success(searchPetList);
    }

    /**
     * 작성자 : 정주현
     * 내 반려동물 삭제
     */
    @PatchMapping("/remove")
    public SuccessResponse<String> searchPet(@AuthenticationPrincipal Long userId,
                                                                @RequestParam(name = "petId") Long petId){
        petService.remove(petId);
        return SuccessResponse.successWithoutResult("성공");
    }



}
