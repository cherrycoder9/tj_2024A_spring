package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.model.dto.MemberDto;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    // 1. 전체 카테고리 호출
    public List<Map<String, String>> bcFindAll() {
        System.out.println("BoardService.bcFindAll");
        return boardDao.bcFindAll();
    }

    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;

    // 2.
    public boolean bWrite(final BoardDto boardDto) {
        // 글자 작성을 요청한 회원의 로그인회원번호 구하기
        // 1. 로그인 세션에서 값 호출
        final Object object = memberService.mLoginCheck();
        if (object == null) {
            return false; // 비로그인시 함수 강제종료/취소
        }
        // 2. 세션내 회원번호 속성 호출
        final MemberDto memberDto = (MemberDto) object;
        // 3. 속성 호출
        final int loginNo = memberDto.getNo();
        // 4. BoardDto 담아주기
        boardDto.setNo(loginNo);

        // - 파일 업로드 처리
        if (boardDto.getUploadFile().isEmpty()) { // - 업로드 된 파일이 존재  하지 않으면
        } else { // 존재하면
            final String uploadFileName = fileService.fileUpload(boardDto.getUploadFile());
            // 1. 만약에 업로드가 실패 했으면  글쓰기 실패
            if (uploadFileName == null) {
                return false;
            }
            // 2. BoardDto 에 업로드 된 파일명 담아주기
            boardDto.setBfile(uploadFileName);
        }
        // ---- DB 처리
        return boardDao.bWrite(boardDto);

    }

    // 3. 게시물 전체 조회 처리
    public BoardPageDto bFindAll(final BoardPageDto pageDto) {
        System.out.println("BoardService.bFindAll");
        System.out.println("pageDto = " + pageDto);
        // 만약에 페이지 번호가 매개변수로 존재하지 않으면 1페이지로 설정
        if (pageDto.getPage() == 0) {
            pageDto.setPage(1);
        }
        // 1. 하나의 페이지당 표시할 게시물 수
        final int pageBoardSize = 5; // 5개씩 출력
        // 2. 페이지당 게시물을 출력할 시작레코드 번호
        final int startRow = (pageDto.getPage() - 1) * pageBoardSize;

        // 4. 전체 게시물 수 : 조건추가) 카테고리번호 별, 조건추가) 검색 조건
        final int totalBoardSize = boardDao.getTotalBoardSize(
                pageDto.getBcno(),
                pageDto.getSearchKey(),
                pageDto.getSearchKeyword()
        );
        // 3. totalPage : 전체 페이지수 구하기
        // 총 페이지수 계산식 : 전체 게시물수 / 페이지당 게시물수
        /*
         * ex) 총 게시물수 : 23개, 페이지당 5개씩 게시물 출력
         *
         * */
        final int totalPage = totalBoardSize % pageBoardSize == 0 ?
                totalBoardSize / pageBoardSize :
                (totalBoardSize / pageBoardSize) + 1;

        // 5. 페이지별 시작 버튼 번호, 끝 버튼 번호

        /*
         * ex) 총 게시물수: 63개, 페이지당 5개씩 게시물 출력한다고 가정
         * 12페이지 + 추가게시물3개 -> 13페이지
         * 가정: 페이지당 최대 버튼수를 5개씩
         * 1페이지일 때 버튼 출력 : [1] [2] [3] [4] [5]
         * 2페이지일 때 버튼 출력 : [1] [2] [3] [4] [5]
         * 3페이지일 때 버튼 출력 : [1] [2] [3] [4] [5]
         * 4페이지일 때 버튼 출력 : [1] [2] [3] [4] [5]
         * 5페이지일 때 버튼 출력 : [1] [2] [3] [4] [5]
         * 6페이지일 때 버튼 출력 : [6] [7] [8] [9] [10]
         * 7페이지일 때 버튼 출력 : [6] [7] [8] [9] [10]
         * 8페이지일 때 버튼 출력 : [6] [7] [8] [9] [10]
         * 9페이지일 때 버튼 출력 : [6] [7] [8] [9] [10]
         * 10페이지일 때 버튼 출력 : [6] [7] [8] [9] [10]
         * 11페이지일 때 버튼 출력 : [11] [12] [13]
         * 12페이지일 때 버튼 출력 : [11] [12] [13]
         * 13페이지일 때 버튼 출력 : [11] [12] [13]
         * */

        final int btnSize = 5; // 페이지당 최대 버튼수를 5개씩 표기한다는 가정
        final int startBtn; // 페이지별 시작 버튼 번호 변수
        startBtn = ((pageDto.getPage() - 1) / btnSize) * btnSize + 1;
        int endBtn; // 페이지별 끝
        endBtn = startBtn + btnSize - 1;
        if (endBtn > totalPage) {
            endBtn = totalPage;
        }

        // 6. 게시물 정보 조회: 조건추가1) 페이징처리, 조건추가2)카테고리별
        final List<BoardDto> data = boardDao.bFindAll(
                startRow,
                pageBoardSize,
                pageDto.getBcno(),
                pageDto.getSearchKey(),
                pageDto.getSearchKeyword()
        );

        // 7. 반환 객체 구성
        final BoardPageDto boardPageDto = BoardPageDto.builder()
                .page(pageDto.getPage())
                .totalBoardSize(totalBoardSize)
                .totalPage(totalPage)
                .data(data)
                .startBtn(startBtn)
                .endBtn(endBtn)
                .build();

        return boardPageDto;

    }

    // 4. 게시물 개별 조회 처리
    public BoardDto bFindBno(final int bno) {
        boardDao.bViewIncrease(bno);
        return boardDao.bFindBno(bno);
    }

    // 5. 게시물의 댓글 쓰기 처리, ??? 왜 Mapping 없는지
    // 컨트롤러와 서비스 계층의 역할이 다르기 때문
    // Mapping은 주로 HTTP 요청과 특정 메서드를 연결하기 위해 사용되며 주로 컨트롤러의 역할
    // 서비스 계층은 비즈니스 로직을 처리하는 역할
    public boolean bReplyWrite(final Map<String, String> map) {
        System.out.println("BoardService.bReplyWrite");
        System.out.println("map = " + map);
        // ??? 왜 map
        // 다양한 키-값 쌍을 하나의 객체로 전달하기 위함
        // 댓글 작성 시 여러 데이터 항목을 받아야 하므로 이를 Map으로 전달하면 유연하게 데이터를 처리
        // 다양한 데이터 항목을 손쉽게 처리할 수 있음.
        // 새로운 필드를 추가하거나 제거할 때 DTO 클래스를 수정할 필요가 없음
        // 간단한 데이터를 처리할 때 코드가 간단해짐

        // 작성자(no)는 별도의 클라이언트로부터 입력받는 구조가 아니다
        // ??? 왜 로그인 정보는 세션객체에 저장하는지 ???
        // 사용자가 로그인된 상태를 유지할 수 있음
        // 세션은 서버 측에서 사용자별로 상태를 관리하는 방식
        // 세션에 로그인 정보를 저장하여 이후의 요청에서 이 정보를 참조할 수 있음
        // 매번 로그인 정보를 확인할 필요 없이, 세션에서 로그인 상태를 유지하고 쉽게 접근
        final Object object = memberService.mLoginCheck(); // ??? 왜 Object 타입에 넣는지
        // 메서드가 반환할 수 있는 객체의 타입이 명확하지 않거나 다양한 타입을 반환할 수 있기 때문
        if (object == null) {
            return false; // 비로그인시 함수 강제종료/취소
        }
        final MemberDto memberDto = (MemberDto) object;
        final int no = memberDto.getNo();
        map.put("no", String.valueOf(no));

        return boardDao.bReplyWrite(map); // ??? 왜 dao 이동하는지
        // 데이터베이스와의 상호작용을 담당하기 위해
        // 비즈니스 로직과 데이터 접근 로직을 분리하여 코드의 유지보수성과 확장성을 높이기 위해 사용
    }

    // 6. 게시물에 달린 댓글 출력 처리 Service (Map 자료형 사용)
    public List<Map<String, String>> bReplyList(final int bno) {
        return boardDao.bReplyList(bno);
    }

}
























