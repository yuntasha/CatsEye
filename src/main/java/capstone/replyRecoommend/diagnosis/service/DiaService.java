package capstone.replyRecoommend.diagnosis.service;

import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



public interface DiaService {
    DiaDtoRes.diagnosisRes diagnosis(MultipartFile petImage, Long userId, Long petId);


    List<DiaDtoRes.searchDiagnosis> searchDiagnosis(Long userId);
}
