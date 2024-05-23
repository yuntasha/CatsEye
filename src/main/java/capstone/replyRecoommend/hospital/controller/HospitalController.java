package capstone.replyRecoommend.hospital.controller;

import capstone.replyRecoommend.global.response.SuccessResponse;
import capstone.replyRecoommend.hospital.dto.MapResponse;
import capstone.replyRecoommend.hospital.service.HospitalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    private final HospitalServiceImpl hospitalService;
    @GetMapping
    public SuccessResponse<MapResponse> test(@RequestParam String region){
        return SuccessResponse.success((hospitalService.getHospitalList(region)));
    }
}
