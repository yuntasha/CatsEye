package capstone.replyRecoommend.marker.repository;

import capstone.replyRecoommend.marker.domain.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
}
