package capstone.replyRecoommend.pet.domain;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.global.entity.BaseEntity;
import capstone.replyRecoommend.pet.domain.Enum.PetStatus;
import capstone.replyRecoommend.pet.dto.PetDtoReq;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;

    private String name;

    private Integer age;

    private String species;

    private String comment;

    @Column(length = 500)
    private String petImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private PetStatus petStatus;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Diagnosis> diagnosisList = new ArrayList<>();


    //user,pet 연관관계 메소드
    public void setUser(User user){
        // 기존 user와의 관계를 제거
        if (this.user != null) {
            this.user.getPetList().remove(this);
        }
        this.user = user;
        user.getPetList().add(this);
    }

    //==생성 메서드==//
    public static Pet createPet(PetDtoReq.toPetReq request,User user,String fileUrl){
        return Pet.builder()
                    .user(user)
                    .name(request.getName())
                    .age(request.getAge())
                    .species(request.getSpecies())
                    .comment(request.getComment())
                    .petImageUrl(fileUrl)
                .build();
    }
    //==삭제 메서드==//
    public void remove() {
        this.petStatus = PetStatus.DELETE;
    }


}
