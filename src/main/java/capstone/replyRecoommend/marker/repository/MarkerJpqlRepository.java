package capstone.replyRecoommend.marker.repository;

import capstone.replyRecoommend.marker.domain.Marker;

import java.util.List;

public interface MarkerJpqlRepository {
    List<Marker> findNear(Double lat, Double lng, Double rng);
    List<Marker> findNearAndType(Double lat, Double lng, Double rng, Marker.Type type);
}
