package capstone.replyRecoommend.marker.util;

import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.marker.web.dto.DataLoadDTO;
import capstone.replyRecoommend.marker.web.dto.HospitalDTO;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;


@Slf4j
public class HospitalUtil {

    /**
     * 병원 정보 가져오기
     * @param xml xml내용
     * @return 병원 리스트
     */
    public static HospitalDTO[] getMapInfo(String xml){
        try {
            return parsingXml(xml);
        } catch (JAXBException e) {
            throw new BusinessException(CommonErrorCode.DATA_FILE_ERROR);
        } catch (IOException e) {
            throw new BusinessException(CommonErrorCode.REGION_NOT_FOUND);
        }
    }

    /**
     * xml파싱
     * @return 병원 리스트
     * @throws JAXBException xml형식과 다를 경우
     * @throws IOException 파일 읽다가 생긴 문제
     */
    public static HospitalDTO[] parsingXml(String xml) throws JAXBException, IOException {
        log.info("xml");
        log.info(xml);
        JAXBContext jaxbContext = JAXBContext.newInstance(DataLoadDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml) ;
        DataLoadDTO mapResponse = (DataLoadDTO) unmarshaller.unmarshal(reader);
        return mapResponse.getHospitalArray();
    }
}
