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

    @Builder
    @Getter
    public static class flaskRes{
        private String result;
    }

    @Builder
    @Getter
    public static class diagnosisRes{
        private Long diagnosisId;
        private String petName;
        private DiagnosisResult result;
        private LocalDateTime time;
    }

}
