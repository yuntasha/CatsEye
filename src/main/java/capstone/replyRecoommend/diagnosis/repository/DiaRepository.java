package capstone.replyRecoommend.diagnosis.repository;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.pet.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaRepository extends JpaRepository<Diagnosis,Long> {

    List<Diagnosis> findByPet(Pet pet);

    List<Diagnosis> findAllByPet_User(User user);


}
