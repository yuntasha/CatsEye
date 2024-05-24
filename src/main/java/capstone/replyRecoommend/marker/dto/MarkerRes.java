package capstone.replyRecoommend.marker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MarkerRes {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MarkerPointRes{
        private Long id;
        private String facility;
        private String type;
        private String lat;
        private String lng;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MarkerDetailRes{
        private Long id;
        private String facility;
        private String tel;
        private String streetNameAddress;
        private String address;
    }
}
