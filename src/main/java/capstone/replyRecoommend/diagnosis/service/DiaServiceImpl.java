package capstone.replyRecoommend.diagnosis.service;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.repository.UserRepository;
import capstone.replyRecoommend.config.S3Uploader;
import capstone.replyRecoommend.diagnosis.converter.DiaConverter;
import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;
import capstone.replyRecoommend.diagnosis.repository.DiaRepository;
import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.pet.domain.Pet;
import capstone.replyRecoommend.pet.repository.PetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaServiceImpl implements DiaService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final DiaRepository diaRepository;
    private final S3Uploader s3Uploader;
    private final ObjectMapper objectMapper;

/*
    //진단하기(사진 업로드)
    @Override
    public DiaDtoRes.searchDiagnosis diagnosis(MultipartFile petImage, Long userId, Long petId) throws JsonProcessingException {
        Pet pet = petRepository.findById(petId).orElseThrow(()->new BusinessException(CommonErrorCode.PET_NOT_FOUND));

        String fileUrl = s3Uploader.upload(petImage,"DiaImage");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        //파라미터로 들어온 dto를 JSON 객체로 변환
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = objectMapper.writeValueAsString(petImage);

        HttpEntity<String> entity = new HttpEntity<>(param , headers);








    }

 */


    @Override
    //진단 내역 조회
    public List<DiaDtoRes.searchDiagnosis> searchDiagnosis(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(CommonErrorCode.USER_NOT_FOUND));

        List<Diagnosis> diagnosisList = diaRepository.findAllByPet_User(user);

        if (diagnosisList.isEmpty()) {
            throw new BusinessException(CommonErrorCode.PET_NOT_FOUND);
        }

        return diagnosisList.stream()
                .map(DiaConverter::searchDiaDto)
                .collect(Collectors.toList());

    }


}
