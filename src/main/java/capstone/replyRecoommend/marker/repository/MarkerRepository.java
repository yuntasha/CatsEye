package capstone.replyRecoommend.marker.repository;

import capstone.replyRecoommend.marker.domain.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkerRepository extends JpaRepository<Marker, Long>, MarkerJpqlRepository {


    /**
     * 유저 위치 정보 기반으로 근처 병원/약국 찾기
     * @param lat 위도
     * @param lng 경도
     * @param rng 범위
     * @return 근처 병원/약국(리스트) 리스트
     */
    List<Marker> findNear(Double lat, Double lng, Double rng);

    /**
     * 유저 위치 정보 기반으로 근처 병원만 혹은 약국만 찾기
     * @param lat
     * @param lng
     * @param rng
     * @param type
     * @return
     */
    List<Marker> findNearAndType(Double lat, Double lng, Double rng, Marker.Type type);
}
