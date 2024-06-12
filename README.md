<h1>YouTube Data API 로 Player 만들기</h1>
<h3>진행 결과 / 24.06.12 트래픽 줄이기</h3>
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

<h3>트래픽 줄이기</h3>
Youtube Data API의 하루 트래픽 수 가 정해져있어 여러번 테스트 하다보니 트래픽제한이 걸려 다음 날 까지 사용할 수 가없었다.<br>
조금이라도 줄여보면 어떨가싶어서 검색을 해보니 여러 방법이 있었다.<br>
1. 필드 선택 최소화 : 필요한 필드만 가져오는 방법<br>
2. 페이징 : 한번에 여러개를 가져오는것이 아닌 필요한 만큼씩만 요청해서 가져오는 방법<br>
3. 캐싱 : 클라이언트 측 캐싱 또는 서버 측 캐싱을 통해 동일한 요청에 대한 응답을 재사용하는 방법<br>
여러 방법 중 나는 필드 클라이언트 측 캐싱을 통해 트래픽을 줄여보았다.<br>
자주 사용하는 검색어에 대한 결과를 캐싱해서 동일한 요청이 반복될 때 API 요청을 줄인다. @Cacheable 어노테이션을 사용했다.<br><br>


<h5>캐싱이란?</h5>
<p>데이터를 미리 저장해두고, 필요한 경우 해당 데이터를 빠르게 제공하여 시스템의 성능을 향상 시키는 기술이다.<br>
캐싱의 주요 목적은 자주 요청되는 데이터를 빠르게 제공하여 시스템의 응답시간을 줄이고 서버의 부하를 줄이며,<br>
네크워크 트래픽을 감소시키는 것. 트래픽을 관리할 때 유용하다.
</p>

<h5>변경 전</h5>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/63c1cbed-b7a6-4c8e-85ad-fafd426d4076">
<h5>변경 후</h5>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/962545f7-853b-467b-80c1-c57c3f629b00">
<p>
  @Cacheable 어노테이션으로 메서드가 호출될 때 캐시를 먼저 확인하고 캐시에 값이 있으면 API를 호출없이 캐시된 값을 반환한다. <br>
  캐시에 값이 없으면 API를 호출하고 캐시에 결과를 저장한다.
  캐싱이 잘 적용되는지 확인하기 위해 log 를 찍어 확인해본다.
</p>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/5667cf2d-5a9e-4ce8-9648-5a6236dc84d4"><br>
동일한 검색을 요청했을 때 로그가 한 번만 나오면 캐싱이 잘 적용된 것이다.<br><br>

동일한 검색을 두 번 요청했는데 처음보다 빠르게 반환되었다. 잘 적용된 듯.

<hr>

<h3>Controller</h3>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/0b4993b4-04d7-49f9-a5a6-1de8a9c46a6e">

<h3>Service</h3>
<img width="700" alt="image" src="https://github.com/jjsh0208/youtube_player/assets/128787964/3e0d6c92-6b9a-4f81-a238-4647d040cc99">
