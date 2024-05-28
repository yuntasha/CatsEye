package capstone.replyRecoommend.diagnosis.domain;


import capstone.replyRecoommend.diagnosis.domain.Enum.DiagnosisResult;
import capstone.replyRecoommend.global.entity.BaseEntity;
import capstone.replyRecoommend.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Builder
@DynamicInsert
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="pet_id")
    private Pet pet;

    @Enumerated(EnumType.STRING)
    private DiagnosisResult diagnosisResult;

    private String diagnosisImageUrl;

    private LocalDateTime diagnosisDay;

    //==생성 메서드==//
    public static Diagnosis createDiagnosis(Pet pet,String fileUrl, DiagnosisResult diagnosisResult){
        return Diagnosis.builder()
                .pet(pet)
                .diagnosisResult(diagnosisResult)
                .diagnosisImageUrl(fileUrl)
                .diagnosisDay(LocalDateTime.now())
                .build();
    }

}
