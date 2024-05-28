package capstone.replyRecoommend.diagnosis.controller;

import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;
import capstone.replyRecoommend.diagnosis.service.DiaService;
import capstone.replyRecoommend.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/diagnosis")
public class DiaController {

    private final DiaService diaService;

    /**
     * 작성자 : 정주현
     * 내 반려동물 진단하기(사진 업로드)
     */

    @PostMapping("/upload")
    public SuccessResponse<DiaDtoRes.diagnosisRes> diagnosis(@RequestPart MultipartFile petImage, @RequestParam Long petId,
                                                             @AuthenticationPrincipal Long userId){
        DiaDtoRes.diagnosisRes str = diaService.diagnosis(petImage,userId,petId);

        return SuccessResponse.success(str);
    }

    /**
     * 작성자 : 정주현
     * 내 반려동물 진단 내역 조회
     */

    @GetMapping("/search")
    public SuccessResponse<List<DiaDtoRes.searchDiagnosis>> searchDiagnosis(//@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                                            // @RequestParam(value = "limit", defaultValue= "10") int limit,
                                                                            @AuthenticationPrincipal Long userId){

        List<DiaDtoRes.searchDiagnosis> searchDiagnosisList = diaService.searchDiagnosis(userId);

        return SuccessResponse.success(searchDiagnosisList);

    }

}