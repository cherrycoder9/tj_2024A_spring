package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired private BoardDao boardDao;
    // 1. 전체 카테고리 호출
    public List< Map<String,String> > bcFindAll( ) {
        System.out.println("BoardService.bcFindAll");
        return boardDao.bcFindAll();
    }

    @Autowired MemberService memberService;
    @Autowired FileService fileService;
    // 2.
    public boolean bWrite( BoardDto boardDto) {
        // 글자 작성을 요청한 회원의 로그인회원번호 구하기
        // 1. 로그인 세션에서 값 호출
        Object object = memberService.mLoginCheck();
        if( object == null ) return  false; // 비로그인시 함수 강제종료/취소
        // 2. 세션내 회원번호 속성 호출
        MemberDto memberDto = (MemberDto)object;
        // 3. 속성 호출
        int loginNo = memberDto.getNo();
        // 4. BoardDto 담아주기
        boardDto.setNo( loginNo );

        // - 파일 업로드 처리
            if( boardDto.getUploadFile().isEmpty() ){ // - 업로드 된 파일이 존재  하지 않으면
            }else{ // 존재하면
                String uploadFileName = fileService.fileUpload( boardDto.getUploadFile() );
                // 1. 만약에 업로드가 실패 했으면  글쓰기 실패
                if( uploadFileName == null ) return false;
                // 2. BoardDto 에 업로드 된 파일명 담아주기
                boardDto.setBfile( uploadFileName );
            }
        // ---- DB 처리
        return boardDao.bWrite( boardDto );

    }
    // 3. 게시물 전체 조회 처리
    public List<BoardDto> bFindAll(){
        return boardDao.bFindAll();
    }
    // 4. 게시물 개별 조회 처리
    public BoardDto bFindBno( int bno ){
        return boardDao.bFindBno( bno );
    }

}
























