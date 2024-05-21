package capstone.replyRecoommend.diagnosis.domain;

import capstone.replyRecoommend.diagnosis.domain.Enum.DiagnosisResult;
import capstone.replyRecoommend.global.entity.BaseEntity;
import capstone.replyRecoommend.pet.domain.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
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

}
