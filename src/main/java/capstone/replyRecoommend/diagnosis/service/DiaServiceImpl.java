package capstone.replyRecoommend.diagnosis.service;

import capstone.replyRecoommend.global.auth.domain.User;
import capstone.replyRecoommend.global.auth.repository.UserRepository;
import capstone.replyRecoommend.config.S3Uploader;
import capstone.replyRecoommend.diagnosis.converter.DiaConverter;
import capstone.replyRecoommend.diagnosis.domain.Diagnosis;
import capstone.replyRecoommend.diagnosis.domain.Enum.DiagnosisResult;
import capstone.replyRecoommend.diagnosis.dto.DiaDtoRes;
import capstone.replyRecoommend.diagnosis.repository.DiaRepository;
import capstone.replyRecoommend.diagnosis.web.FlaskWebClient;
import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.pet.domain.Enum.PetStatus;
import capstone.replyRecoommend.pet.domain.Pet;
import capstone.replyRecoommend.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaServiceImpl implements DiaService {

    private final FlaskWebClient flaskWebClient;

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final DiaRepository diaRepository;
    private final S3Uploader s3Uploader;


    //진단하기(사진 업로드)
    @Override
    @Transactional
    public DiaDtoRes.diagnosisRes diagnosis(MultipartFile petImage, Long userId, Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(()->new BusinessException(CommonErrorCode.PET_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(()->new BusinessException(CommonErrorCode.USER_NOT_FOUND));

        if(!pet.getUser().equals(user)){
            throw new BusinessException(CommonErrorCode.PET_USER_NOT_MATCH);
        }


        String fileUrl = s3Uploader.upload(petImage,"DiaImage");

        // Flask 서버에 전송할 요청 본문을 생성한다
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("fileUrl", fileUrl);

        Map<String, String> result = flaskWebClient.flaskReq(requestPayload);

        DiagnosisResult diagnosisResult;

        if(result.get("result").equals("abnormal")){
            diagnosisResult = DiagnosisResult.ABNORMAL;
        }
        else{
            diagnosisResult = DiagnosisResult.NORMAL;
        }

        Diagnosis diagnosis = Diagnosis.createDiagnosis(pet,fileUrl,diagnosisResult);

        diaRepository.save(diagnosis);

        LocalDateTime dateTime = diagnosis.getDiagnosisDay();

        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);

        return DiaConverter.diagnosisRulDto(diagnosis,pet,date,formattedTime);


    }


    @Override
    //진단 내역 조회
    public List<DiaDtoRes.searchDiagnosis> searchDiagnosis(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(CommonErrorCode.USER_NOT_FOUND));

        List<Diagnosis> diagnosisList = diaRepository.findAllByPet_UserAndPet_PetStatus(user, PetStatus.ACTIVE);

        if (diagnosisList.isEmpty()) {
            throw new BusinessException(CommonErrorCode.DIAGNOSIS_NOT_FOUND);
        }

        return diagnosisList.stream()
                .map(DiaConverter::searchDiaDto)
                .collect(Collectors.toList());

    }


}