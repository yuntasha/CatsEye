package capstone.replyRecoommend.marker.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rfcOpenApi")
public class DataLoadDTO {

    @XmlElement(name = "body")
    private XmlBody xmlBody;

    public HospitalDTO[] getHospitalArray(){
        this.getXmlBody().getMapResponseDTO().cleanList();
        return this.getXmlBody().getMapResponseDTO().getList();
    }
}
