console.log( "view.js() ")

// board.js 에서 view 페이지 이동 코드  <th> <a href="/board/view?bno=${ b.bno }">${ b.btitle }</a>
    // JS 코드가 HTML 를 만들어내고 사용자는 표현된 HTML 에서 a 클릭이벤트
    // <a href="/board/view?bno=3">안녕하세요</a>    ---> /board/view?bno=3

// URL 상의 쿼리스트링 매개변수를 JS에서 꺼내는방법
    // JAVA SPRING 에서 HTTP URL 상의 쿼리스트링 매개변수를 꺼내는 방법
        // @RequestParam 이용한 쿼리스트링 매개변수 매핑
    // JS 에서 HTTP URL 상의 쿼리스트링 매개변수를 꺼내는 방법
        // 1. new URL( location.href )  : 현재 JS가 포함된 URL 경로 의 정보가 담긴 객체 호출
        // 2. .searchParams;            : 현재경로상의 쿼리스트링 매개변수 속성 호출
        // 3. .get( key )               : 쿼리스트링 매개변수의 key에 해당 하는 value 호출
let bno = new URL( location.href ).searchParams.get('bno'); // 현재 URL 경로상의 bno 값 호출
console.log( bno );
// 1. 개별 게시물 출력 , 매개변수는 현재 게시물의 번호
doBoardFindBno( bno )
function doBoardFindBno( bno ){
    let board = {}
    $.ajax({ // AJAX
        async : false , method : "get" ,
        url :"/board/find/bno", data : { bno : bno } ,
        success : r => { console.log(r); board = r} // 응답 받은 데이터을 ajax 밖 변수에 대입
    }) // AJAX END
    document.querySelector('.bcName').innerHTML = `${ board.bcname }`;
    document.querySelector('.etcBox').innerHTML = `${ board.id } / ${ board.bview } / ${ board.bdate }`;
    document.querySelector('.bTitle').innerHTML = `${ board.btitle }`;
    document.querySelector('.bContent').innerHTML = `${ board.bcontent }`;
    document.querySelector('.bFile').innerHTML = `${ board.bfile } <a href="/file/download?filename=${ board.bfile }">다운로드</a>`;
    document.querySelector('.btnBox').innerHTML =
            `
            <button type="button" onclick="location.href='/board/update?bno=${bno}'">수정</button>
            <button type="button" onclick="doBoardDelete(${bno})">삭제</button>
            `;
}