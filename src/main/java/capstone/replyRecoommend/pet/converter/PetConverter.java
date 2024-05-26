package capstone.replyRecoommend.pet.converter;

import capstone.replyRecoommend.pet.domain.Pet;
import capstone.replyRecoommend.pet.dto.PetDtoRes;

import java.util.List;

public class PetConverter {

    //나의 반려동물 조회 DTo 변환
    public static PetDtoRes.searchPet searchPet(Pet pet){
        return PetDtoRes.searchPet.builder()
                    .perId(pet.getId())
                    .name(pet.getName())
                    .age(pet.getAge())
                    .species(pet.getSpecies())
                    .comment(pet.getComment())
                    .petImageUrl(pet.getPetImageUrl())
                    .build();
    }

    public static PetDtoRes.enrollPet enrollPetRes(Pet pet){
        return PetDtoRes.enrollPet.builder()
                .PetId(pet.getId())
                .build();
    }

}
