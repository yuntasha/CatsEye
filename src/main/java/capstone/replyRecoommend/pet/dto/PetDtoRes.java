package capstone.replyRecoommend.pet.dto;

import lombok.Builder;
import lombok.Getter;

public class PetDtoRes {

    @Getter
    @Builder
    public static class searchPet{
        private Long perId;
        private String name;
        private Integer age;
        private String species;
        private String comment;
        private String petImageUrl;

    }

    @Getter
    @Builder
    public static class enrollPet{
        private Long PetId;
    }
}
