package capstone.replyRecoommend.hospital.service;

import capstone.replyRecoommend.hospital.dto.MapResponse;
import capstone.replyRecoommend.hospital.util.HospitalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl {

    public MapResponse getHospitalList(String region){
        return HospitalUtil.getMapInfo(region);
    }
}
