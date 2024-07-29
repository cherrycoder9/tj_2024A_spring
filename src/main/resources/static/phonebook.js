console.log( 'phonebook.js 열림')
//let phonebookDB = [ ]
// 1. 등록함수 : 등록버튼을 클릭했을때.
function postPhone(){
    let name = document.querySelector('#name').value;   // 1. 입력받은 값 호출 해서 변수에 저장
    let phone = document.querySelector('#phone').value;
    let phoneDto = { name : name , phone : phone  } // 2. 객체화
    //phonebookDB.push( phoneDto ) // 3. 객체를 배열에 저장
    //alert('save');  getPhone();  // 4. 안내 / 새로고침
    // 2. html에 jquery 라이브러리 가져왔으면 ajax 함수 사용 가능.
        // 2-1 ajax 들어갈 옵션 객체 정의
    let option = {
        url : "http://localhost:8080/phone/create" ,   // 통신할 경로 --> spring의 controller
        method : "post", // HTTP가 지원하는 함수중 사용할 함수명( post , get , put , delete 등 )
        data : JSON.stringify( phoneDto ) , // 통신할 경로에 보낼 데이터 --> spring의 controller 에게 보낼 데이터
        contentType : "application/json" , // data 옵션에 있는 타입
        success : function response( result ){ // 통신을 성공했을때 응답받을 함수 정의 하고 매개변수는 응답의 데이터가 들어온다.
            console.log( result );
            if( result ){ alert('save'); getPhone(); }
            else{ alert('fail'); }
        } // success response end
    } // option { } end
        // 2-2 ajax 함수 호출    , $ : jquery문법
    $.ajax( option );

} // postPhone 함수 end

// 2. 출력함수 : 등록처리가 되었을때 , js열렸을때 최초1번
getPhone();
function getPhone(){
    let phoneListBox = document.querySelector('#phoneListBox') // 1. 어디에
    let html = ''; // 2. 무엇을      // JAVA : ->  , js : =>
    let option = {
        url : "http://localhost:8080/phone/read" ,          // 누구에게
        method : "get",                                     // 어떠한 방식으로
        success : function response( result ){              // 무엇을 받을지
            console.log( result );
            result.forEach(  phone  => {
                html +=  `<div>
                            <span> ${ phone.name } </span>
                            <span> ${ phone.phone } </span>
                        </div>`
            })
            phoneListBox.innerHTML = html // 3. 출력
        }
    } // ajax 통신 option 설정 end
    // ajax 함수 실행
    $.ajax( option );   // $ is not defined : jquery 라이브러리 존재하지 않는다.
}
