package capstone.replyRecoommend.marker.controller;

import capstone.replyRecoommend.global.response.SuccessResponse;
import capstone.replyRecoommend.marker.domain.Marker;
import capstone.replyRecoommend.marker.dto.MarkerRes;
import capstone.replyRecoommend.marker.service.MarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/marker")
public class MarkerController {

    private final MarkerService markerService;

    @GetMapping
    public SuccessResponse<List<MarkerRes.MarkerPointRes>> getMarkerList(){
        return SuccessResponse.success((markerService.getMarkerList()));
    }

    @GetMapping("near")
    public SuccessResponse<List<MarkerRes.MarkerPointRes>> getNearMarkerList(@RequestParam(name = "lat") String lat
            , @RequestParam(name = "lng") String lng, @RequestParam(name = "type", required = false) String type){
        return SuccessResponse.success((markerService.getNearList(lat, lng, Marker.Type.setType(type))));
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
