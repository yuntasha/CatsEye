package capstone.replyRecoommend.diagnosis.converter;

import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;

public class DiaConverter {

    public static DiaDtoRes.searchDiagnosis searchDiaDto(Diagnosis diagnosis){
        return DiaDtoRes.searchDiagnosis.builder()
                .time(diagnosis.getDiagnosisDay())
                .name(diagnosis.getPet().getName())
                .diagnosisResult(diagnosis.getDiagnosisResult())
                .diagnosisImageUrl(diagnosis.getDiagnosisImageUrl())
                .build();
    }
}
