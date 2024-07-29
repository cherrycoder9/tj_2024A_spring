console.log( 'login.js' )

// 1. 로그인
function doLogin(){
    // 1. 입력받은 값 가져오기
    let id = document.querySelector('#id').value
    let pw = document.querySelector('#pw').value
    // 2. 객체화
    let info = { id : id , pw : pw }; console.log( info );
    // 3. AJAX 통신
    $.ajax({
        async : false ,
        method : 'post' ,
        url : '/member/login',
        data : info ,
        success : (result)=>{ console.log( result );
            // 4. 결과에 따른 처리
            if( result ){
                alert('로그인성공');
                location.href="/";
            }else{
                alert('로그인실패');
            }
        } // success end
    }); // ajax end

}