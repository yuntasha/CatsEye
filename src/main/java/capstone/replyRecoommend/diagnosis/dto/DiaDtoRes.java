package capstone.replyRecoommend.diagnosis.dto;

import capstone.replyRecoommend.diagnosis.domain.Enum.DiagnosisResult;
import lombok.*;

import java.time.LocalDateTime;

public class DiaDtoRes {

    @Builder
    @Getter
    public static class searchDiagnosis{
        private LocalDateTime time;
        private String name;
        private DiagnosisResult diagnosisResult;
        private String diagnosisImageUrl;
    }
}
