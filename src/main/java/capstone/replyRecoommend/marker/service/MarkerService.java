package capstone.replyRecoommend.marker.service;

import capstone.replyRecoommend.marker.domain.Marker;
import capstone.replyRecoommend.marker.dto.MarkerRes;

import java.util.List;

public interface MarkerService {
    List<MarkerRes.MarkerPointRes> getMarkerList();
    List<MarkerRes.MarkerPointRes> getNearList(String lat, String lng, Marker.Type type);
    MarkerRes.MarkerDetailRes getDetails(Long id);
    void loadData();

}
