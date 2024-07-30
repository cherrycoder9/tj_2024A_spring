console.log("board.js");

// 1. 전체 게시물 조회
doBoardFindAll(1);
function doBoardFindAll(page, bcno) {
    let boardPageDto = {}; // ajax로부터 응답받은 객체를 저장하는 변수 

    $.ajax({
        async: false,  // false동기화 vs true비동기화( innerHTML 후 에 응답 온다.)
        method: "get",
        url: "/board/find/all",
        data: { page: page, bcno: bcno },
        success: r => {
            console.log(r);
            // 응답 데이터의 타입이 리스트 인지 객체1개 인지 확인 필요.
            boardPageDto = r;
        }
    });
    // 1. 어디에
    let boardBody = document.querySelector('.boardBody');
    // 2. 무엇을
    let html = '';
    //
    let list = boardPageDto.data;
    list.forEach(b => {
        html += `<tr>
                    <th> ${b.bno} </th>
                    <th> <a href="/board/view?bno=${b.bno}">${b.btitle}</a>
                    </th> <th> ${b.id} </th>
                    <th> ${b.bdate} </th>
                    <th> ${b.bview}</th>
                    </tr>`;
    });
    // 3. 출력
    boardBody.innerHTML = html;

    // 4. 페이지네이션(페이지 버튼) 구성 
    // 4-1 어디에
    let pagination = document.querySelector('.pagination');
    // 4-2 무엇을 
    let pageHTML = ``;
    // 이전 버튼
    pageHTML += `<li class="page-item"><a class="page-link" onclick="doBoardFindAll(${page - 1 < 1 ? 1 : page - 1})">이전</a></li>`;
    // 페이지 버튼
    // 페이지마다 시작 버튼 수:
    let startBtn = boardPageDto.startBtn;
    // 페이지마다 끝 버튼 수:
    let endBtn = boardPageDto.endBtn;
    // 최대 페이지수: totalPage 
    let totalPage = boardPageDto.totalPage;

    // current는 startBtn부터 endBtn까지
    for (let current = startBtn; current <= endBtn; current++) {
        pageHTML += `<li class="page-item"><a class="page-link ${current === page ? 'active' : ''}" onclick="doBoardFindAll(${current})">${current}</a></li>`;
    }
    // 다음 버튼, page: 현재 함수의 매개변수 이면서 현재 페이지번호 의미, 만약에 현재페이지 +1 했을때 최대페이지보다 
    pageHTML += `<li class="page-item"><a class="page-link" onclick="doBoardFindAll(${page + 1 > totalPage ? totalPage : page + 1})">다음</a></li>`;

    // 4-3 출력
    pagination.innerHTML = pageHTML;
}