// src/main/resources/static/js/board/view.js

console.log("view.js() ");

// 2. 댓글 쓰기


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
let bno = new URL(location.href).searchParams.get('bno'); // 현재 URL 경로상의 bno 값 호출
console.log(bno);
// 1. 개별 게시물 출력 , 매개변수는 현재 게시물의 번호
doBoardFindBno(bno);

function doBoardFindBno(bno) {
    let board = {};
    $.ajax({ // AJAX
        async: false, method: "get",
        url: "/board/find/bno", data: { bno: bno },
        success: r => {
            console.log(r);
            board = r;
        } // 응답 받은 데이터을 ajax 밖 변수에 대입
    }); // AJAX END

    // 카테고리명 출력
    document.querySelector('.bcName').innerHTML = `<span class="badge bg-primary">${board.bcname}</span>`;

    // 작성자, 조회수, 작성일 출력
    document.querySelector('.etcBox').innerHTML = `
        <div class="text-muted">${board.id}</div>
        <div class="text-muted">조회수: ${board.bview}</div>
        <div class="text-muted">${board.bdate}</div>
    `;

    // 제목 출력
    document.querySelector('.bTitle').innerHTML = `<h4>${board.btitle}</h4>`;

    // 내용 출력
    document.querySelector('.bContent').innerHTML = `<p>${board.bcontent}</p>`;

    // 첨부파일 출력
    document.querySelector('.bFile').innerHTML = board.bfile ?
        `<div class="mt-3"><span class="text-info">${board.bfile.split('_').pop()}</span> 
        <a class="btn btn-sm btn-outline-primary" href="/file/download?filename=${board.bfile}">다운로드</a></div>` :
        "<div class='mt-3 text-muted'>첨부파일 없음</div>";

    // 버튼 출력
    document.querySelector('.btnBox').innerHTML = `
        <button type="button" class="btn btn-warning" onclick="location.href='/board/update?bno=${bno}'">수정</button>
        <button type="button" class="btn btn-danger" onclick="doBoardDelete(${bno})">삭제</button>
    `;
} // doBoardFindBno END

// 2. 댓글 쓰기 
function onReplyWrite() {
    console.log("onReplyWrite()");

    // 1.  
    let brcontent = document.querySelector('.brcontent').value;
    console.log(brcontent);

    let = info = { brcontent: brcontent, brindex: 0, bno: bno };
    $.ajax({
        async: false,
        method: "post",
        url: "/board/reply/write",
        data: JSON.stringify(info),
        contentType: "application/json",
        success: r => {
            console.log(r);
            if (r == true) {
                alert('댓글쓰기 성공');
                document.querySelector('.brcontent').value = ``;
            } else {
                alert('댓글쓰기 실패: 로그인해주세요.');
            }
        } // End of success
    }); // End of AJAX
}; // onReplyWrite END