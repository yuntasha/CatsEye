package capstone.replyRecoommend.diagnosis.converter;

import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;
import capstone.replyRecoommend.pet.domain.Pet;

public class DiaConverter {

    public static DiaDtoRes.searchDiagnosis searchDiaDto(Diagnosis diagnosis){
        return DiaDtoRes.searchDiagnosis.builder()
                .time(diagnosis.getDiagnosisDay())
                .name(diagnosis.getPet().getName())
                .diagnosisResult(diagnosis.getDiagnosisResult())
                .diagnosisImageUrl(diagnosis.getDiagnosisImageUrl())
                .build();
    }

    public static DiaDtoRes.diagnosisRes diagnosisRulDto(Diagnosis diagnosis, Pet pet){
        return DiaDtoRes.diagnosisRes.builder()
                .diagnosisId(diagnosis.getId())
                .petName(pet.getName())
                .result(diagnosis.getDiagnosisResult())
                .time(diagnosis.getDiagnosisDay())
                .build();
    }
}
