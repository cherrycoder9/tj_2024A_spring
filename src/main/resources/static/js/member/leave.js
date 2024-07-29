console.log( 'leave.js' )
// [1] 회원탈퇴 함수
function doLeave(){ console.log('doLeave()');
    // 1.
    let pwConfirm = document.querySelector('.pwConfirm').value;
    console.log( pwConfirm );
    // 2. AJAX 이용한 서버에게 탈퇴 통신
    $.ajax({
        method : 'delete' ,                 // HTTP METHOD : 통신할때 사용할 method 선택
        url : "/member/leave",              // HTTP URL : 통신할 경로
        data : { pwConfirm : pwConfirm } ,  // HTTP 보내는 DATA
        success : (result)=>{               // HTTP 받는 DATA
            if( result ){ alert('회원탈퇴 했습니다.'); location.href="/"; }
            else{ alert('로그인정보와 일치하지 않습니다.'); }
        },
        error : ( e )=>{
            console.log( e );
        }
    }); // ajax end
} // method end