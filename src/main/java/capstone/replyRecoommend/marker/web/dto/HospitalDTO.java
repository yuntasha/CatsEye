package capstone.replyRecoommend.marker.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class HospitalDTO {
    @XmlElement(name = "unqId")
    private int unqId;
    @XmlElement(name = "city")
    private String city;
    @XmlElement(name = "facility")
    private String facility;
    @XmlElement(name = "tel")
    private String tel;
    @XmlElement(name = "streetNameAddress")
    private String streetNameAddress;
    @XmlElement(name = "address")
    private String address;
    @XmlElement(name = "lat")
    private String lat;
    @XmlElement(name = "lng")
    private String lng;

    void cleanString(){
        this.city = stringCleaner(this.city);
        this.facility = stringCleaner(this.facility);
        this.tel = stringCleaner(this.tel);
        this.streetNameAddress = stringCleaner(this.streetNameAddress);
        this.address = stringCleaner(this.address);
        this.lat = stringCleaner(this.lat);
        this.lng = stringCleaner(this.lng);
    }

    private String stringCleaner(String before){
        return before != null ? before.trim().replaceAll("(^\\s+|\\s+$)", ""):null;
    }
}