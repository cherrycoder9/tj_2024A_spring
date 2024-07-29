console.log( "board.js")

// 1. 전체 게시물 조회
doBoardFindAll();
function doBoardFindAll(){
    let list = []
    $.ajax({
        async : false ,  // false동기화 vs true비동기화( innerHTML 후 에 응답 온다.)
        method : "get" ,
        url : "/board/find/all" ,
        success : r => {    console.log( r );
            // 응답 데이터의 타입이 리스트 인지 객체1개 인지 확인 필요.
            list = r;
        }
    })
    // 1. 어디에
    let boardBody = document.querySelector('.boardBody')
    // 2. 무엇을
    let html = ''
        //
        list.forEach( b =>{
            html += `<tr>
                    <th> ${ b.bno } </th>
                    <th> <a href="/board/view?bno=${ b.bno }">${ b.btitle }</a>
                    </th> <th> ${ b.id } </th>
                    <th> ${ b.bdate } </th>
                    <th> ${ b.bview }</th>
                    </tr>`
        })
    // 3. 출력
    boardBody.innerHTML = html;
}