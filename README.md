<div align="center">
  
## 💽 랄라리아 : LALALIA (BackEnd)
<img width="330" alt="image" src="https://github.com/isthisteamisthis/.github/assets/119282494/8e02f14a-df51-469b-ae4c-01a76b61154a">
<br>

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fcca-ffodregamdi%2Frunning-hi-back&count_bg=%23FFA49F&title_bg=%24555555&icon=&icon_color=%24E7E7E7&title=views&edge_flat=false)](https://hits.seeyoufarm.com)

</div>
<br>

<br>

### 🎤 프로젝트, LALALIA
>  **직접 제작한 곡이 세상에 불려지길 원하는 당신을 위해,** <br>
>  **목소리를 세상에 알리고 싶은 당신을 위해 준비했습니다.** <br>
>  **랄라리아는 커뮤니티 기반의 커버 노래 공유 & 매칭 플랫폼입니다.**

<br>
<br>

### 🎤 주요 기능 소개
> #### 1️⃣ **본인의 음역대를 찾아주는 기능** <br>
> **가수 지망생**으로 어플에 진입하면, 본인이 낼 수 있는 명확한 음역대에 맞는 노래를 추천받을 수 있습니다. <br>
> **작곡가**로 어플에 진입하게 되면, 본인이 작곡한 노래의 음역대를 명시하여 본인의 노래를 불러줄 가수 지망생을 찾을 수 있습니다.

<br>

> #### 2️⃣ **퍼펙트 스코어 기능** <br>
> 퍼펙트 스코어 기능을 통해 가수 지망생은 노래의 음을 더욱 더 정확하게 낼 수 있도록 연습할 수 있습니다.

<br>

> #### 3️⃣ **AI 커버 기능** <br>
> 가수 지망생은, 노래 실력이 성장한 자신을 직접 마주할 수 있으며, <br>
> 작곡가는 유명인의 목소리를 합성해 들어봄으로서 본인의 노래에 어울리는 음색을 찾을 수 있습니다. <br>

<br>

<br>


## 👋 백엔드 개발 팀원 소개
<div align="center">
<table>
  <tr>
    <td align="center"><a href="https://github.com/numerical43"><img src="https://avatars.githubusercontent.com/numerical43" width="150px;" alt="">
    <td align="center"><a href="https://github.com/Dylan-SonJungin"><img src="https://avatars.githubusercontent.com/Dylan-SonJungin" width="150px;" alt="">
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/numerical43"><b>강수의</b></td>
    <td align="center"><a href="https://github.com/Dylan-SonJungin"><b>손정인</b></td>
  </tr>
  <tr>
    <td align="center">Kang Su Ui</td>
    <td align="center">Son Jung In</td>
  </tr>
    <tr>
    <td align="center"><strong>카카오로그인, 회원, 게시글, 좋아요, 쪽지</strong></td>
    <td align="center"><strong>클라이언트, flask 서버 통신</strong></td>
  </tr>
</table>
</div>
<br>
<br>

## 🛠 기술 스택
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white">

<br>
<br>

## Client - Flask 서버 통신
<div align="center">

<img width="580" alt="image" src="https://github.com/isthisteamisthis/lalalia_back/assets/88484476/d9d9d7b1-7c64-4503-a126-801073a6c3ca">

</div>

<br>

<br>

  
## ⭐️ 프로젝트 기능 소개
### 로그인
    - 카카오 로그인 API 호출을 통한 소셜 로그인 사용
    - 발급된 토큰값을 Async Storage에 저장하여 페이지 이동시에도 회원 인증
### 음역대 측정 & 추천곡 생성
    - 회원의 가장 높은 음, 낮은 음을 녹음하여 AI flask 서버에 전달하여 음역대 추출
    - 회원의 음역대를 기반으로 맞춤형 곡 추천 리스트를 생성하여 서버에 저장
### 나만의 노래방!
    - MR과 자막이 제공됨
    - 부른 노래 음원 파일을 Firebase Storage 에 저장
    - 부른 노래 음원 파일을 AI flask 서버에 전달하여 점수 추출
    - 생성된 점수를 회원의 평균점수에 반영하여 DB에 저장

### AI 커버곡 생성
    - 회원이 원본 파일과 커버 이미지를 업로드
    - 원하는 목소리 모델과 참조율(닮은 정도), 옥타브를 선택하여 생성 요청
    - 음원 파일과 선택사항을 AI flask 서버에 전달
    - 생성된 AI음원 파일을 Firebase Storage 에 저장

### 커뮤니티
    - 나의 곡들을 다른 회원들과 공유
    - 좋아요 기능

### 쪽지
    - 함께 작업해보고 싶은 사람에게 쪽지를 보낼 수 있음





