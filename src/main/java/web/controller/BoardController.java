package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.service.BoardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 1. 전체 카테고리 호출
    @GetMapping("/category")
    // public List<BoardDto> bcFindAll(){
    public List<Map<String, String>> bcFindAll() {
        System.out.println("BoardController.bcFindAll");
        return boardService.bcFindAll();
    }

    // 2. 글 쓰기 처리
    @PostMapping("/write")
    // { "bcno" : 1 ,  "btitle" : "안녕" ,  "bcontent" : "하하하" }
    public boolean bWrite(final BoardDto boardDto) {
        System.out.println("BoardController.bWrite");
        System.out.println("boardDto = " + boardDto);
        return boardService.bWrite(boardDto);
    }

    // 3. 게시물 전체 조회 처리
    @GetMapping("/find/all")
    public BoardPageDto bFindAll(
            final BoardPageDto pageDto
            // 매개변수
            // 1. page: 페이징 처리에서 사용할 현재 페이지 번호
            // 2. bcno: 현재 선택된 카테고리 번호
            // 3. searchKey: 검색 조회시 사용되는 필드명
            // 4. searchKeyword: 검색 조회시 사용되는 필드의 값
    ) {
        return boardService.bFindAll(pageDto);
    }

    // 4. 게시물 개별 조회 처리
    @GetMapping("/find/bno")
    public BoardDto bFindBno(final int bno) {
        return boardService.bFindBno(bno);
    }

    // 5. 게시물의 댓글 쓰기 처리
    @PostMapping("/reply/write") // ??? 왜 포스트 매핑
    // 댓글 쓰기 작업은 일반적으로 서버에 데이터를 생성하는 작업이기 때문에 POST 요청을 사용하는 것이 적절
    public boolean bReplyWrite(@RequestBody final Map<String, String> map) {
        // @RequestBody는 클라이언트가 요청 본문에 담아 보내는 데이터를 메서드 파라미터로 받기 위해 사용
        // HTTP 요청 본문에 담긴 JSON, XML 또는 다른 형식의 데이터를 자바 객체로 변환하여 메서드 파라미터로 받기 위해 사용
        // POST, PUT 요청에서 클라이언트가 서버로 전송하는 데이터가 JSON이나 XML 형식일 때 사용

        // 반면에 @RequestParam 은 URL 쿼리 스트링 또는 폼 데이터에서 파라미터 값을 추출하여 메서드 파라미터로 받기 위해 사용
        // 주로 GET 요청이나 폼 데이터 전송에서 사용
        // ?key=value 형식의 데이터를 받을 때 사용
        // @RequestBody는 요청 본문에서 데이터를 가져오고
        // @RequestParam은 URL 쿼리 스트링이나 폼 데이터에서 데이터를 가져옴
        // @RequestBody는 JSON, XML 등의 형식을 지원하며, 이를 자바 객체로 변환
        // @RequestParam은 주로 단순한 문자열이나 기본 자료형을 사용
        System.out.println("BoardController.bReplyWrite");
        System.out.println("map = " + map);
        // ??? 왜 map
        // 클라이언트가 보내는 JSON 데이터를 Map<String, String> 타입의 map 객체로 변환하여 사용
        // 댓글 쓰기와 관련된 여러 데이터를 한 번에 받을 수 있는 간편한 방법
        return boardService.bReplyWrite(map); // ??? 왜 service
        // 비즈니스 로직을 컨트롤러와 분리하여 코드의 재사용성과 유지보수성을 높이기 위함

        // 참고
        // POST: 요청된 자원에 데이터를 추가함. 주로 서버에서 새롭게 생성된 리소스의 URI를 반환
        // 멱등성이 없음. 동일한 요청을 여러 번 보내면 서버에 여러 개의 리소스가 생성될 수 있음
        // PUT: 서버에 존재하는 리소스를 업데이트하거나, 존재하지 않으면 새로 생성하기 위해 사용
        // 멱등성 있음. 동일한 요청을 여러 번 보내도 결과는 동일
        // 전체 리소스를 업데이트하거나, 리소스가 존재하지 않으면 새롭게 생성
        // 멱등성 연산: 동일한 연산을 여러 번 적용하더라도 결과가 달라지지 않는 성질을 의미
    }
}

















