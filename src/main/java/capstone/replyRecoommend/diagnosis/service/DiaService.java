package capstone.replyRecoommend.diagnosis.service;

import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DiaService {
    //public DiaDtoRes.searchDiagnosis diagnosis(MultipartFile petImage, Long userId,Long petId) throws JsonProcessingException;


    List<DiaDtoRes.searchDiagnosis> searchDiagnosis(Long userId);
}
