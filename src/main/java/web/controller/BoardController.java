package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
            final BoardPageDto pageDto // 1. 페이징 처리에서 사용할 현재 페이지 번호
    ) {
        return boardService.bFindAll(pageDto);
    }

    // 4. 게시물 개별 조회 처리
    @GetMapping("/find/bno")
    public BoardDto bFindBno(final int bno) {
        return boardService.bFindBno(bno);
    }
}

















