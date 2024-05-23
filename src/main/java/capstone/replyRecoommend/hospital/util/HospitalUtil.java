package capstone.replyRecoommend.hospital.util;

import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.hospital.dto.MapResponse;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.IOException;


@Slf4j
public class HospitalUtil {

    /**
     * 병원 정보 가져오기
     * @param region
     * @return
     */
    public static MapResponse getMapInfo(String region){
        try {
            return parsingXml(region);
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
    private static MapResponse parsingXml(String region) throws JAXBException, IOException {

        FileInputStream fileInputStream = new FileInputStream("data/" + region + ".xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(MapResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        MapResponse mapResponse = (MapResponse) unmarshaller.unmarshal(fileInputStream);

        fileInputStream.close();
        mapResponse.cleanList();
        return mapResponse;
    }
}
