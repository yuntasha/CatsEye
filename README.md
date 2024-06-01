# [REST API 서버] "고양이 눈 질병 진단/챗봇 서비스"

![image](https://github.com/yuntasha/replyRecoommend/assets/131857282/9f908fa2-7db7-4294-9c01-f6eaea364b68)


> 초기에 발생되는 눈 질병에 대해서 빠르게 진단하고 챗봇에게 상당받는 서비스
>
---

### 🧑‍🤝‍🧑 맴버구성
 - 팀원1 : 신재희 - 구글 소셜로그인, CICD 구축, 지도 관련(map), 초기 gpt api 세팅
 - 팀원2 : 정주현 - 인공지능 모델, diagnosis(CRUD), pet(CRUD), Chat-GPT 챗봇제작, flask서버 연동-springboot 서버 연동
 - 팀원3 : 최지안 - 프론트엔드
 - 팀원4 : 강은서 - 보고서 작성


---

## 🍩 프로젝트 개요

### 서비스 소개
- 고양이 눈의 질병을 진단해주는 서비스
- 진단 후 인근 병원혹은 챗봇에게 문의해보는 서비스
- 초기 진단으로 졀병을 예방하는 서비스



### 진행사항
- 울산대학교 졸업작품(캡스톤 디자인)
---

## 🍠서버 구조도

### 1) 프론트 - 백엔드 - flask - gpt
![image](https://github.com/yuntasha/replyRecoommend/assets/131857282/f2966369-4b96-465b-9b2b-d39ec9814d1d)


>- AWS EC2 : FreeTier t2.micro
>- OS : Ubuntu 22.04 LTS
>- Java : Oracle Open JDK "17"
>- SpringBoot : 3.2.4
>- AWS RDS : Freetier t2.micro 버스터블 클래스 & MySQL 8.0.33 
---

### 2) AWS 내부 아키텍처

![image](https://github.com/yuntasha/replyRecoommend/assets/131857282/32f50f1c-40cf-46df-af29-7ed09fa03f38)

---


### 3) CICD 흐름도

<img width="420" alt="image" src="https://github.com/yuntasha/replyRecoommend/assets/131857282/346c7eb6-f8fd-4b17-b214-019b972f6237">


---
## 🌮기능 정의

#### 1. 소셜로그인(구글)
- 회원가입, 본인인증, 로그인, 로그아웃, 내정보 조회

#### 2. 반려동물
- 반려동물 추가, 반려동물 삭제
- 반려동물 목록 조회

#### 3. 진단하기
- 반려동물 진단하기(사진 업로드)
- 반려동물 진단 내역 조회

#### 4. 챗봇(Chat GPT)
- 채팅 시작
- 채팅방 삭제

#### 5. 주변 약국 지도
- 지도에 병원/약국 표시
- 병원/약국 세부정보
- 인근 병원/약국 조회


---
## ☕핵심 기능
### (구현 완료)
- transfer learning을 사용하여 훈련된 ResNet 모델 사용
- 구글 소셜로그인
- Chat GPT API 연동


---
## 🍚플로우 차트
![image](https://github.com/yuntasha/replyRecoommend/assets/131857282/21425978-15b4-417f-8aef-f7ff1c1ef630)


--- 
## 🧀ERD
![erd](https://github.com/yuntasha/replyRecoommend/assets/131857282/28798dc9-4e9f-4775-9dde-ddc24c4f15f9)
