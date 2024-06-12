package capstone.replyRecoommend.pet.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class PetDtoReq {

    @Getter
    public static class toPetReq{
        private String name;
        private Integer age;
        private String species;
        private String comment;
    }

}