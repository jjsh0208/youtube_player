// 모달 요소를 가져옵니다
var modal = document.getElementById("videoModal");

// 모달을 닫는 <span> 요소 (x 버튼)를 가져옵니다
var span = document.getElementsByClassName("close")[0];

// 동영상을 여는 함수입니다
function openVideo(element) {
    modal.style.display = "block";
    var url = element.getAttribute('data-url');
    document.getElementById("videoFrame").src = url;
}

// <span> (x)를 클릭하면 모달을 닫습니다
span.onclick = function() {
    modal.style.display = "none";
    // 모달이 닫힐 때 iframe의 src를 비워서 동영상 재생을 중지합니다
    document.getElementById("videoFrame").src = "";
}

// 사용자가 모달 영역 바깥을 클릭하면 모달을 닫습니다
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
        document.getElementById("videoFrame").src = "";
    }
}