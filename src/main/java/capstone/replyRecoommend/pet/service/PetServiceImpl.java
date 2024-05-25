package capstone.replyRecoommend.pet.service;

import capstone.replyRecoommend.auth.domain.User;
import capstone.replyRecoommend.auth.repository.UserRepository;
import capstone.replyRecoommend.config.S3Uploader;
import capstone.replyRecoommend.global.exception.BusinessException;
import capstone.replyRecoommend.global.exception.errorcode.CommonErrorCode;
import capstone.replyRecoommend.pet.converter.PetConverter;
import capstone.replyRecoommend.pet.domain.Enum.PetStatus;
import capstone.replyRecoommend.pet.domain.Pet;
import capstone.replyRecoommend.pet.dto.PetDtoReq;
import capstone.replyRecoommend.pet.dto.PetDtoRes;
import capstone.replyRecoommend.pet.repository.PetRepository;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetServiceImpl implements PetService{

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    public final S3Uploader s3Uploader;

    @Override
    //반려동물 등록 메소드
    @Transactional
    public PetDtoRes.enrollPet toPet(PetDtoReq.toPetReq request, Long userId, MultipartFile file){
        User user = userRepository.findById(userId).orElseThrow(()->new BusinessException(CommonErrorCode.USER_NOT_FOUND));

        String fileUrl = s3Uploader.upload(file,"petImage"); //fileUrl

        Pet pet = Pet.createPet(request,user,fileUrl);

        petRepository.save(pet);

        return PetConverter.enrollPetRes(pet);
    }

    @Override
    //내 반려동물 목록 조회
    public List<PetDtoRes.searchPet> searchPet(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new BusinessException(CommonErrorCode.USER_NOT_FOUND));

        List<Pet> petList = petRepository.findByUserAndPetStatus(user, PetStatus.ACTIVE); //ACTIVE상태만 조회

        List<PetDtoRes.searchPet> searchPetList = petList.stream()
                .map(o->PetConverter.searchPet(o))
                .collect(Collectors.toList());

        if(searchPetList.isEmpty()){
            throw new BusinessException((CommonErrorCode.PET_NOT_FOUND));
        }

        return searchPetList;
    }

    //반려동물 삭제
    @Override
    @Transactional
    public void remove(Long petId){
        Pet pet =petRepository.findById(petId).orElseThrow(()->new BusinessException(CommonErrorCode.PET_NOT_FOUND));

        pet.remove(); //Pet status를 DELETE로 수정

        petRepository.save(pet);
    }





}
