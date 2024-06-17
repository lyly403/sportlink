// JavaScript 파일에서 상세 페이지 초기화 및 데이터 표시
document.addEventListener('DOMContentLoaded', function () {
  // URL에서 데이터 가져오기
  var urlParams = new URLSearchParams(window.location.search);
  var title = urlParams.get('title');
  var date = urlParams.get('date');
  var location = urlParams.get('location');
  var price = urlParams.get('price');
  var count = urlParams.get('count');
  var category = urlParams.get('category');
  var textArea = urlParams.get('textArea');

  // 데이터가 존재할 경우에만 출력
  if (title && date && location && price && count && category && textArea) {
    document.getElementById('detailTitle').textContent = title;
    document.getElementById('detailDate').textContent = date;
    document.getElementById('detailLocation').textContent = location;
    document.getElementById('detailPrice').textContent = price;
    document.getElementById('detailCount').textContent = count;
    document.getElementById('detailCategory').textContent = category;
    document.getElementById('detailTextArea').textContent = textArea;
  }

  // 구매 버튼 클릭 시 처리
  const purchaseButton = document.getElementById('purchaseButton');
  purchaseButton.addEventListener('click', function () {
    // 구매 내역 페이지로 데이터 전송
    var orderData = {
      title: title,
      date: date,
      location: location,
      price: price,
      count: count,
      category: category
    };

    // 로컬스토리지에 저장 (간단한 예시)
    localStorage.setItem('orderData', JSON.stringify(orderData));

    // '구매완료' 알림창 표시
    alert('구매완료!');

    // 주문 내역 페이지로 이동
    window.location.href = "/sportLink/pages/user/order.html";
  });
});
// 날씨 //


const API_KEY = "9cbefddcf0b1e2af09c146b2df82ad9c"; //add your API KEY
const COORDS = 'coords'; //좌표를 받을 변수

//DOM객체들
const weatherInfo = document.querySelector('.weatherInfo');
const weatherIconImg = document.querySelector('.weatherIcon');

//초기화
function init() {
    askForCoords();
}

//좌표를 물어보는 함수
function askForCoords() {
    navigator.geolocation.getCurrentPosition(handleSuccess, handleError);
}

//좌표를 얻는데 성공했을 때 쓰이는 함수
function handleSuccess(position) {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
    const coordsObj = {
        latitude,
        longitude
    };
    getWeather(latitude, longitude); //얻은 좌표값을 바탕으로 날씨정보를 불러온다.
}
//좌표를 얻는데 실패했을 때 쓰이는 함수
function handleError() {
    console.log("can't not access to location");
}

// 날씨 api를 통해 날씨에 관련된 정보들을 받아온다.
function getWeather(lat, lon) {
    fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`)
        .then(function (response) {
            return response.json();
        })
        .then(function (json) {
            // 온도와 날씨 아이콘 정보를 받아온다.
            const temperature = json.main.temp;
            const weatherIcon = json.weather[0].icon;
            const weatherDescription = convertWeatherDescription(json.weather[0].description); // 영문 설명을 한글로 변환

            // 영문으로 받은 날씨 아이콘 정보를 한글로 변환한다.
            const weatherIconCode = json.weather[0].icon;
            const weatherIconAdrs = `https://openweathermap.org/img/wn/${weatherIcon}@2x.png`;

            // 받아온 정보들을 표현한다.
            const weatherIconHTML = `<img src="${weatherIconAdrs}" alt="Weather Icon" style="display: inline-block; vertical-align: middle;">`;
            const temperatureHTML = `<span style="display: inline-block; vertical-align: middle; margin-right: 20px;">${temperature} °C</span>`;
            const weatherDescriptionHTML = `<span style="display: inline-block; vertical-align: middle; margin-left: 20px;">${weatherDescription}</span>`;
            weatherInfo.innerHTML = `${weatherIconHTML}${temperatureHTML}`;
        })
        .catch((error) => console.log("error:", error));
}

// 영문으로 받은 날씨 설명을 한글로 변환하는 함수
function convertWeatherDescription(description) {
    switch (description) {
        case "clear sky":
            return "맑음";
        case "few clouds":
            return "구름 조금";
        case "scattered clouds":
            return "구름 많음";
        case "broken clouds":
            return "구름 낌";
        case "shower rain":
            return "소나기";
        case "rain":
            return "비";
        case "thunderstorm":
            return "천둥 번개";
        case "snow":
            return "눈";
        case "mist":
            return "안개";
        default:
            return description;
    }
}
init();