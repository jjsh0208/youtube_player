<h1>YouTube Data API 로 Player 만들기</h1>
<h3>진행 결과</h3>
<img width="958" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/bd1aa4e2-3582-4d8d-aa7f-df663b677072">
<hr>
<h3>1. Google API Console에서 프로젝트 생성 및 유튜브 데이터 API 활성화</h3>
1. https://console.developers.google.com/ 에 접속해 새 프로젝트를 생성한다.<br>
2. 생성한 프로젝트에서 "Library"로 이동하여 "YouTube Data API"를 찾은 후 활성화한다.<br>
<img width="500" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/2a4b34cd-cee5-4926-9286-1ea22f038295"><br>
3. "Credentials" (사용자 인증 정보) 에서 API 키를 생성한다.<br>
<img width="500" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/e77b9eac-c950-444d-b045-8f88bf9c62a8">

<hr>
<h3>Spring Boot 설정</h3>
YouTube Data API 와 통신하기 위해 필요한 의존성 추가
build.gradle : <br>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/6f36c35f-8090-4c5d-9a14-7555f4a04cc8">

<hr>

<h3>Controller</h3>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/0b4993b4-04d7-49f9-a5a6-1de8a9c46a6e">

<h3>Service</h3>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/3e0d6c92-6b9a-4f81-a238-4647d040cc99">
