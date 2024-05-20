package capstone.replyRecoommend.pet.domain;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.pet.domain.Enum.PetStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Pet {

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

    private String petImageUrl;

    private PetStatus petStatus;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Diagnosis> diagnosisList = new ArrayList<>();

}
