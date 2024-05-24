package capstone.replyRecoommend.marker.domain;

import capstone.replyRecoommend.global.entity.BaseEntity;
import capstone.replyRecoommend.marker.dto.MarkerRes;
import capstone.replyRecoommend.marker.web.dto.HospitalDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter
@Table(name = "MAPNODE")
public class Marker extends BaseEntity {

    @Id @Column(name = "node_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;
    @Column(name = "facility")
    private String facility;
    @Column(name = "tel")
    private String tel;
    @Column(name = "streetNameAddress")
    private String streetNameAddress;
    @Column(name = "address")
    private String address;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lng")
    private String lng;

    @Column(name = "node_type")
    @Enumerated(EnumType.STRING)
    private Marker.Type type;

    public Marker(HospitalDTO hospitalDTO, Marker.Type type){
        this.city = stringCleaner(hospitalDTO.getCity());
        this.facility = stringCleaner(hospitalDTO.getFacility());
        this.tel = stringCleaner(hospitalDTO.getTel());
        this.streetNameAddress = stringCleaner(hospitalDTO.getStreetNameAddress());
        this.address = stringCleaner(hospitalDTO.getAddress());
        this.lat = stringCleaner(hospitalDTO.getLat());
        this.lng = stringCleaner(hospitalDTO.getLng());
        this.type = type;
    }

    private static String stringCleaner(String before){
        return before != null ? before.trim().replaceAll("(^\\s+|\\s+$)", ""):null;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        HOSPITAL("Hospital"),
        PHARMACY("Pharmacy");

        private final String value;
    }

    public MarkerRes.MarkerPointRes toMarkerPoint(){
        return MarkerRes.MarkerPointRes.builder()
                .id(this.id)
                .facility(this.facility)
                .type(this.type.getValue())
                .lat(this.lat)
                .lng(this.lng)
                .build();
    }

    public MarkerRes.MarkerDetailRes toDetail(){
        return MarkerRes.MarkerDetailRes.builder()
                .id(this.id)
                .facility(this.facility)
                .tel(this.tel)
                .address(this.address)
                .streetNameAddress(this.streetNameAddress)
                .build();
    }
}
