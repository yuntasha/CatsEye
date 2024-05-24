package capstone.replyRecoommend.marker.controller;

import capstone.replyRecoommend.global.response.SuccessResponse;
import capstone.replyRecoommend.marker.dto.MarkerRes;
import capstone.replyRecoommend.marker.service.MarkerServiceImpl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/marker")
public class MarkerController {

    private final MarkerServiceImpl markerService;

    @GetMapping
    public SuccessResponse<List<MarkerRes.MarkerPointRes>> getMarkerList(){
        return SuccessResponse.success((markerService.getMarkerList()));
    }

    @GetMapping("details/{id}")
    public SuccessResponse<MarkerRes.MarkerDetailRes> getMarkerDetail(@PathVariable Long id){
        return SuccessResponse.success((markerService.getDetails(id)));
    }


    @GetMapping("data")
    public SuccessResponse<Object> getData(){
        markerService.loadData();
        return SuccessResponse.successWithoutResult(null);
    }
}
