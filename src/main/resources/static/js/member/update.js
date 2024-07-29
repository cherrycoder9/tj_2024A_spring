console.log( 'update.js' )

// 1. 내정보 호출
doMyInfo();
function doMyInfo(){
    $.ajax({
        method : 'get',
        url : "/member/my/info",
        success : result =>{ console.log( result );
            if( result == '' ){
                alert('로그인하고 오세요');  location.href="/member/login";
            }
            //
            document.querySelector('.no').innerHTML = `${result.no}`;
            document.querySelector('.id').innerHTML = `${result.id}`;
            document.querySelector('.name').value = `${result.name}`;
            document.querySelector('.phone').value = `${result.phone}`;
            document.querySelector('.email').innerHTML = `${result.email}`;

        }
    })
}
// 2. 수정처리
function doUpdate(){ console.log( 'doUpdate()' )

    // 1. 보낼 데이터 수집.
    let pw = document.querySelector('.pw').value;
    let newPw = document.querySelector('.newPw').value;
    let name = document.querySelector('.name').value;
    let phone = document.querySelector('.phone').value;

    let info = {  pw : pw ,   newPw : newPw ,
        name : name ,  phone : phone
    }
    // 2.
    $.ajax({
        async : false ,
        method : "put" ,
        url : "/member/update",
        data : JSON.stringify( info ) ,
        contentType : "application/json" ,
        success : r => { console.log(r);
            if( r ){
                alert('수정성공');
                location.href="/member/mypage"; }
            else{
                alert('입력한 정보가 일치하지 않습니다. ');
            }
        } ,
        error : e =>{ console.log(e); }
    })

}


















