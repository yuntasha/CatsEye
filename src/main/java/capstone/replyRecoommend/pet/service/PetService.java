package capstone.replyRecoommend.pet.service;

import capstone.replyRecoommend.pet.dto.PetDtoReq;
import capstone.replyRecoommend.pet.dto.PetDtoRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetService {
    PetDtoRes.enrollPet toPet(PetDtoReq.toPetReq request, Long userId, MultipartFile file);
    List<PetDtoRes.searchPet> searchPet(Long userId);
}
