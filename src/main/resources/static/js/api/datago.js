/*
    - 공공데이터 : https://www.data.go.kr
        - 공공기관에서 제공하는 정보들
        - 1. 로그인 ( 간편로그인 )
        - 2. API 검색
        - 3. [활용 신청]
*/

// 1. 인천시 부평구 주유소 현황
api1();
function api1(){
    $.ajax({
        async : false, method: "get" ,
        url : "https://api.odcloud.kr/api/15102672/v1/uddi:5e2a4b30-28fb-4e8f-bc44-9a6db8a6a8db?page=1&perPage=39&serviceKey=nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D" ,
        success : r => { console.log(r);
            /* r : 다양한 속성을 가진 응답 객체
                - r.data : 응답 정보 내용물 */
            let dataArray = r.data;     console.log( dataArray );

            let html = ``;
            dataArray.forEach( data => {
                html += `<tr>
                            <td>${ data['상호'] }</td>
                            <td>${ data['전화번호'] }</td>
                            <td>${ data.주소 }</td>
                        </tr>`
            })
            document.querySelector('.api1Tbody').innerHTML = html;
        }
    })
}
// 2. 인천시 동구 약국 현황 , 검색 : 인천시 동구 약국
api2();
function api2(){
    $.ajax({
        async : false , url : "get",
        url : "https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=35&serviceKey=nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D" ,
        success : r => { console.log(r);
            // 1. 어디에
            let api2Tbody = document.querySelector('.api2Tbody');
            // 2. 무엇을
            let html = ``;
                // -
                r.data.forEach( data => {
                    html += `<tr>
                                 <td>${ data.약국명 }</td>
                                 <td>${ data.전화번호 }</td>
                                 <td>${ data.소재지도로명주소 }</td>
                             </tr>`
                }) // forEach end
            // 3. 출력
            api2Tbody.innerHTML = html;
            // 4. 카카오지도출력 함수에 데이터 매개변수 전달
            api3( r.data );
        } // success end
    }) // ajax end
} // f end

// 3. 카카오 지도 API
/*
    1. 카카오개발자센터 : https://developers.kakao.com/
    2. 로그인
    3. 플랫폼 신청
        1. 상단메뉴 -> [ 내 애플리케이션 ] -> [애플리케이션 추가 ]
    4.애플리케이션 선택/클릭
        사이드바 메뉴
            [ 앱키 ] : 카카오API 사용할때 사용되는 인증키
                JavaScript 키 확인 , 카카오지도 활용
                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 넣으시면 됩니다."></script>

            [ 플랫폼 ] : 카카오API 사용할 web URL 등록
                Web 플랫폼 등록
                    http://localhost:8080
                    http://192.168.30.9:8080
*/
function api3( data ){  // 매개변수 : api2에서 전달받은 약국정보리스트
    // 1. 지도를 담을 영역의 DOM 레퍼런스
    var container = document.querySelector('#map');
    // 2. 지도를 생성할 때 필요한 기본 옵션
    var options = {
        //지도의 중심좌표. 위도Lat , 경도Lng , 인천시청 : 37.4562557, 126.7052062
        center: new kakao.maps.LatLng(37.4562557, 126.7052062),
        //지도의 레벨(확대, 축소 정도) 0(최대확대) ~ 14(최대축소)
        level: 7
    };
    // 3. 지도 생성 및 객체 리턴
    var map = new kakao.maps.Map( container, options);

    // 4. 마커를 표시할 위치와 title 객체 배열입니다.
        // positions : 여러개 마커의 위도/경도  ,  { } : 하나의 마커의 위도/경도 , latlng : new kakao.maps.LatLng( 위도 , 경도 )
            // - 1. 리스트명.forEach( 반복요소 => {  } );                               : 리스트내 요소를 하나씩 반환해서 반복
            // - 2. let 반환리스트 = 리스트명.map( 반복요소 => {  return 반환값 } );       : 리스트내 요소를 하나씩 반환해서 반복
                // 차이점 : 실행문 결과값을 반환 가능 , 반복 반환 결과를 배열로 최종 반환
        // data는 api2 함수에서는 공공데이터포털의 인천 동구 약국 정보를 매개변수로 전달 받은 객체
        /*
    let positions = [ ]
    for( let i = 0 ; i < data.length ; i++ ){
        // 1. 객체 생성
        let location = { title : data[i].약국명 , latlng : new kakao.maps.LatLng( data[i].위도 , data[i].경도 ) }
        positions.push( location );
    }
    console.log( positions );
    */
    // ------------------------------ vs
    let positions = data.map(  d => {
                // 1. 객체 생성
                let location = { title : d.약국명 , latlng : new kakao.maps.LatLng( d.위도 , d.경도 )  }
                // 2. 객체 리턴
                return location; // 반복하면서 생성된 객체를 반환해서 positions 배열에 최종 대입
            });
    console.log( positions );


    // 5. 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    // 6. 반복문을 이용한 여러개 마커 생성
    for (var i = 0; i < positions.length; i ++) {

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image : markerImage // 마커 이미지
        });
    }

} // api3() end
