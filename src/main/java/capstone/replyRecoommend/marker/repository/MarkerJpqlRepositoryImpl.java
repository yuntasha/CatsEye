package capstone.replyRecoommend.marker.repository;

import capstone.replyRecoommend.marker.domain.Marker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarkerJpqlRepositoryImpl implements MarkerJpqlRepository{
    @PersistenceContext
    private EntityManager em;

    /**
     * 유저 위치 정보 기반으로 근처 병원/약국 찾기
     * @param lat 위도
     * @param lng 경도
     * @param rng 범위
     * @return 근처 병원/약국(리스트) 리스트
     */
    public List<Marker> findNear(Double lat, Double lng, Double rng){

        TypedQuery<Marker> query = em.createQuery(
                "SELECT m FROM Marker m " +
                        "WHERE m.lat BETWEEN :minLat AND :maxLat " +
                        "AND m.lng BETWEEN :minLng AND :maxLng"
                , Marker.class);

        query.setParameter("minLat", String.valueOf(lat-rng));
        query.setParameter("maxLat", String.valueOf(lat+rng));
        query.setParameter("minLng", String.valueOf(lng-rng));
        query.setParameter("maxLng", String.valueOf(lng+rng));

        return query.getResultList();
    }

    public List<Marker> findNearAndType(Double lat, Double lng, Double rng, Marker.Type type){


        TypedQuery<Marker> query = em.createQuery(
                "SELECT m FROM Marker m " +
                        "WHERE m.lat BETWEEN :minLat AND :maxLat " +
                        "AND m.lng BETWEEN :minLng " +
                        "AND :maxLng AND m.type = :type"
                , Marker.class);

        query.setParameter("minLat", String.valueOf(lat-rng));
        query.setParameter("maxLat", String.valueOf(lat+rng));
        query.setParameter("minLng", String.valueOf(lng-rng));
        query.setParameter("maxLng", String.valueOf(lng+rng));
        query.setParameter("type", type);

        return query.getResultList();
    }
}
