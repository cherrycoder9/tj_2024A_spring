console.log( 'myinfo.js' )

// 1. 내정보 호출
doMyInfo();
function doMyInfo(){
    $.ajax({
        method : 'get',
        url : "/member/my/info",
        success : result =>{
            console.log( result );
            if( result == '' ){
                alert('로그인하고 오세요');
                location.href="/member/login";
            }
            // 1. 어디에
            let myInfoBox = document.querySelector('#myInfoBox')
            // 2. 무엇을
            let html ='';
                html += `<div> No : ${ result.no } </div>`
                html += `<div> ID : ${ result.id } </div>`
                html += `<div> name : ${ result.name } </div>`
                html += `<div> phone : ${ result.phone } </div>`
                html += `<div> email : ${ result.email } </div>`
            // 3. 출력
            myInfoBox.innerHTML = html;
        }
    })
}
