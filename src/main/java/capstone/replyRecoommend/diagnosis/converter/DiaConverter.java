package capstone.replyRecoommend.diagnosis.converter;

import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;
import capstone.replyRecoommend.pet.domain.Pet;

import java.time.LocalDate;

public class DiaConverter {

    public static DiaDtoRes.searchDiagnosis searchDiaDto(Diagnosis diagnosis){
        return DiaDtoRes.searchDiagnosis.builder()
                .day(diagnosis.getDiagnosisDay().toLocalDate())
                .name(diagnosis.getPet().getName())
                .diagnosisResult(diagnosis.getDiagnosisResult())
                .diagnosisImageUrl(diagnosis.getDiagnosisImageUrl())
                .build();
    }

    public static DiaDtoRes.diagnosisRes diagnosisRulDto(Diagnosis diagnosis, Pet pet, LocalDate day, String time){
        return DiaDtoRes.diagnosisRes.builder()
                .diagnosisId(diagnosis.getId())
                .petName(pet.getName())
                .result(diagnosis.getDiagnosisResult())
                .day(day)
                .time(time)
                .build();
    }
}
