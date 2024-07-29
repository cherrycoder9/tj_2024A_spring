console.log( 'signup.js' )

/*
    onclick="함수()" : 마우스로 클릭 했을때 작동하는 이벤트
    onkeyup="함수()" : 키보드에서 키를 누르고 떼었을때 작동하는 이벤트
*/

// 2. 아이디 유효성검사
function idCheck(){ console.log('idcheck()')
    // 1. 입력된 값 가져오기
    let id = document.querySelector('#id').value;   console.log( id );
    let idCheckBox = document.querySelector('.idCheckBox');
    // 2. 정규표현식 : 영대소문자와 숫자 조합의 5~30글자 까지 허용
    let idReg =  /^[a-zA-Z0-9]{5,30}$/
    // 3. 정규표현식 검사.
    console.log( idReg.test( id ) )
    if( idReg.test(id) ){
        // 아이디 중복검사 REST API 통신
        $.ajax({
            async : false,              // 비동기true vs 동기false
            method : "get",             // HTTP method
            url : "/member/idcheck",    // HTTP url
            data : { id : id } ,        // HTTP 전송할 DATA
            success : (result)=>{       // HTTP 응답받을 DATA
                if( result ){
                    idCheckBox.innerHTML = `사용중인 아이디`
                    checkArray[0] = false
                }else{
                    idCheckBox.innerHTML = `사용가능한 아이디 입니다.`
                    checkArray[0] = true;
                }
            } // success method end
        }) // ajax end
    }else{
        idCheckBox.innerHTML = `영대소문자 와 숫자 조합의 5~30 글자 사이 가능합니다.`
        checkArray[0] = false;
    }
} // method end

// 3. 패스워드 유효성검사
function pwCheck(){ console.log("pwCheck()");
    // 1.
    let pw = document.querySelector('#pw').value;
    let pwConfirm = document.querySelector('#pwConfirm').value;
    let pwCheckBox = document.querySelector('.pwCheckBox');
    // 2. 정규표현식
    let pwReg = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/
    // 3. 정규표현식 검사
    if( pwReg.test(pw) ){ // 비밀번호 정규표현식 검사
        if( pwReg.test(pwConfirm) ){ // 비밀번호 확인 , 정규표현식 검사
            if( pw == pwConfirm ){ // 두 비밀번호 일치 여부
                pwCheckBox.innerHTML = '통과';
                checkArray[1] = true;
                return;
            }else{
                pwCheckBox.innerHTML = '두 비밀번호가 일치하지 않습니다.'
                checkArray[1] = false;
                return;
            }
        }
    }
    pwCheckBox.innerHTML =`영대소문자 와 숫자 조합의 5~30 글자 사이 가능합니다.`
    checkArray[1] = false;
} // method end

// 4. 이름 유효성검사
function nameCheck(){
    let name = document.querySelector('#name').value;
    let nameCheckBox = document.querySelector('.nameCheckBox')
    let nameReg = /^[가-힣]{2,20}$/
    if( nameReg.test( name ) ){
        nameCheckBox.innerHTML ='사용가능한 이름입니다.';
        checkArray[2] = true;
    }else{
        nameCheckBox.innerHTML ='한글 2~20글자 사이 입력해주세요.';
        checkArray[2] = false;
    }
}
// 5. 전화번호 유효성검사.
function phoneCheck(){
    let phone = document.querySelector('#phone').value;
    let phoneCheckBox = document.querySelector('.phoneCheckBox')
    // 2. 정규표현식 : 000-0000-0000 또는 00-000-0000
    let phoneReg = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]([0-9]{4})$/
    if( phoneReg.test(phone) ){
        // 중복검사 생략
        phoneCheckBox.innerHTML = '사용가능한 전화번호입니다.'
        checkArray[3] = true;
    }else{
        phoneCheckBox.innerHTML = '000-0000-0000 또는 00-000-0000 형식으로 입력해주세요.'
        checkArray[3] = false;
    }
}
// 6. 이메일 유효성검사
function emailCheck(){

    checkArray[4] = false;

    authBtn.disabled = true; // 인증요청 버튼 비활성화
    let email = document.querySelector('#email').value;
    let emailCheckBox = document.querySelector('.emailCheckBox')
    // 2. 정규표현식
        // kgs2072@ : [a-zA-Z0-9_-]+@  : @ 앞에 패턴 1개이상 존재한다.
        // naver : [a-zA-Z0-9_-]
        // .com : \.[a-zA-Z]+
            // . 정규표현식에 사용되는 패턴 vs \. 문자 (점)
    let emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]+$/
    if( emailReg.test(email) ){
        emailCheckBox.innerHTML ='사용가능한 이메일입니다.';
        // 이메일 중복검사 생략
        // 이메일 인증검사
            authBtn.disabled = false; // 인증요청 버튼 활성화
    }else{
        emailCheckBox.innerHTML ='id@도메인주소 형식으로 입력해주세요.';
    }
}

let authBtn = document.querySelector('.authBtn') // * 이메일 인증 버튼
let authBox = document.querySelector('.authBox') // * 인증 구역
let timerInterval = null;                       // * 타이머 인터벌 객체를 저장하는 변수

// 7. 인증 요청 함수
function doAuth(){ console.log('doAuth()')

    checkArray[4] = false;

    // ---*  AJAX 인증 번호 요청 통신
    $.ajax({
        async : false , // 동기식
        method : "get" ,
        url : "/auth/code",
        data : { email : document.querySelector('#email').value } ,
        success : ( result ) => {
            if( result ){ alert('메일로 인증코드 전송 했습니다.'); }
        }
    })

    authBtn.disabled = true; // 인증요청 버튼 비활성화
    // 1. 인증 번호 입력 구역 구성
    let html = `<span class="timerBox"> 00:00 </span>
                <input type="text" class="authCodeInput" />
                <button type="button" class="authCodeBtn" onclick="doAuthCode()" >인증</button>`
    // 2.
    authBox.innerHTML = html;
    // 3. 타이머
    let timer = 10; // 타이머 시간 초
    // 4. 인터벌 ( JS 라이브러리 ) : 특정 주기에 따라 함수를 실행
        // setInterval( 함수정의 , 밀리초 )      // parseInt( ) : 정수 로 타입 변환 ( 소수점 자르기 )
    timerInterval = setInterval( ()=>{
        // 1. 분 , 초 계산
        let m = parseInt( timer / 60 ); // 분
        let s = parseInt( timer % 60 ); // 초
        // 2. 두자릿수 표현
        m = m < 10 ? "0"+m : m; // 만약에 분 이 10보다 작으면 "0" 붙이기
        s = s < 10 ? "0"+s : s;
        // 3. 분 , 초 출력
        document.querySelector('.timerBox').innerHTML = `${m}:${s}`
        // 4. 1초 차감
        timer--; console.log( timer )
        // 5. 만약에 timer 가 -1 이면
        if( timer < 0 ){
            clearInterval( timerInterval ); // 해당 인터벌 종료
            authBox.innerHTML = '다시 인증 요청 해주세요';
            authBtn.disabled = false; // 인증요청 버튼 활성화
        }
    },1000) // setInterval end
} // doauth method end

// 8. 인증코드 인증
function doAuthCode(){
    // 1. 입력한 입력번호 가져오기
    let authCodeInput = document.querySelector('.authCodeInput').value;
    // * 임의의 인증 번호 ( JS 에서 인증번호를 관리하지 않는 이유 : JS는 클라이언트 로부터 오픈코드 이기 떄문에 )
    $.ajax({
        async : false ,  method : "post" , url : "/auth/check",
        data : { authCodeInput , authCodeInput } ,
        success : (result)=>{
            // 2. 만약에 입력한 값이 인증번호와 동일하면 인증 성공
            if( result ){
                authBox.innerHTML = '인증성공';
                clearInterval( timerInterval ); // 인터벌 종료
                checkArray[4] = true;
            }else{
                alert('인증번호가 일치하지 않습니다.')
            }
        }
    });
}

// **** 현재 유효성검사 체크 현황 ****
let checkArray = [ false , false , false , false , false ]
                // 아이디 , 비밀번호,이름 , 전화번호 , 이메일

// 1. 회원가입
function doSignup(){ console.log( 'doSignup()' )

     // 유효성검사 체크
     for( let i = 0 ; i<checkArray.length ; i++ ){
        if( !checkArray[i] ){ // checkArray 의 값이 하나라도 false 이면 함수 종료
            alert('유효하지 않는 정보가 있습니다.')
            return;
        }
     }

    // 1. 입력값 가져오기
    let id = document.querySelector("#id").value;
    let pw = document.querySelector("#pw").value;
    let name = document.querySelector("#name").value;
    let email = document.querySelector("#email").value;
    let phone = document.querySelector("#phone").value;
    // 2. 객체
    let info = {  id : id , pw : pw , name : name ,
                email : email , phone : phone
    }; console.log( info );
    // 3. ajax ( jquery 라이브러리 필요 ) , 비동기 통신
    $.ajax( {
        async : false ,         //  async : true 비동기(기본값) , false 동기식
        method : 'post' ,       // HTTP POST
        url : "/member/signup", // HTTP URL
        data : info ,           // HTTP 보낼 데이터
        success : ( result )=>{ console.log( result ); // HTTP 받을 데이터
            // 4. 결과에 따른 처리
            if( result ){alert('회원가입성공');
                location.href="/member/login";
            }else{  alert('회원가입실패');  }
        } // success end
    } ); // ajax end

    alert('ajax 처리 이후');
    // async : true  ,  alert('ajax 처리 이후'); -> alert('회원가입성공');
    // async : false ,  alert('회원가입성공'); ->  alert('ajax 처리 이후');
} // method end