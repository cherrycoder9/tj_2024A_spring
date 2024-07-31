// src/main/resources/static/js/board/board.js

console.log("board.js");

// * 페이지 정보들을 관리하는 객체, 전역변수
// 1. page: 현재 페이지[기본값 1페이지] 2.bcno: 현재 카테고리 (기본값 0 전체보기)
// 3. searchKey: 현재검색필드(기본값:제목필드) 4. searchKeyword: 현재검색값(기본값:공백)
let pageInfo = {
    page: 1,
    bcno: 0,
    searchKey: "btitle",
    searchKeyword: ""
};

// 5. 검색 상태 제거/초기화 함수 
function onSearchClear() {
    // 1. 입력창 초기화 
    document.querySelector('.searchKey').value = "btitle";
    document.querySelector('.searchKeyword').value = "";
    // 2. 전역변수 초기화
    pageInfo.searchKey = "btitle";
    pageInfo.searchKeyword = "";
    // 3. 새로고침
    doBoardFindAll(1);
}

// 4. 카테고리 클릭 했을때 
function onCategory(bcno) {
    // 검색 제거 
    onSearchClear();
    // 1. 전역변수에 bcno 대입
    pageInfo.bcno = bcno;
    console.log('카테고리 변경: ' + pageInfo.bcno);
    // 2. 새로고침, 1페이지로 
    doBoardFindAll(1);
}


// 3. 카테고리 호출
function getCategory() {
    console.log("getCategory()");
    // 1. 어디에
    let categoryBox = document.querySelector('.categoryBox');

    // 2. 무엇을 
    let html = `<div class="${pageInfo.bcno == 0 ? 'categoryActive' : ''}" style="margin-right: 30px" onclick="onCategory(0)">전체보기</div>`;
    $.ajax({
        async: false,
        method: "get",
        url: "/board/category",
        success: r => {
            console.log(r);
            r.forEach(c => {
                html += `<div class="${pageInfo.bcno == c.bcno ? 'categoryActive' : ''}" style="margin-right: 30px" onclick="onCategory(${c.bcno})">${c.bcname}</div>`;
            });
        },
        error: e => {
            console.log(e);
        }
    });

    // 3. 출력
    categoryBox.innerHTML = html;
}


// 2. 검색 버튼을 클릭했을때 
function onSearch() {
    console.log("onSearch()");
    // 1. 입력받고 
    let searchKey = document.querySelector('.searchKey').value;
    let searchKeyword = document.querySelector('.searchKeyword').value;
    // 2. 전역변수에 대입
    pageInfo.searchKey = searchKey;
    pageInfo.searchKeyword = searchKeyword;
    // 3. 새로고침 
    doBoardFindAll(1);

}

// 1. 전체 게시물 조회
doBoardFindAll(1);
function doBoardFindAll(page) {
    console.log("doBoardFindAll()");
    pageInfo.page = page; // 현재페이지 번호를 전역변수에 대입
    getCategory(); // 카테고리 호출 
    let boardPageDto = {}; // ajax로부터 응답받은 객체를 저장하는 변수 

    $.ajax({
        async: false,  // false동기화 vs true비동기화( innerHTML 후 에 응답 온다.)
        method: "get",
        url: "/board/find/all",
        data: pageInfo,
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
