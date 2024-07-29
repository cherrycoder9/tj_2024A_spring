package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDao extends Dao {
    // 1. 전체 카테고리 호출
    public List< Map<String,String> > bcFindAll( ) {
    // public List< BoardDto > bcFindAll( ) {
        System.out.println("BoardDao.bcFindAll");
        // - list 컬렉션 선언 , map컬렉션(객체) 을 여러개 저장하기 위해 list 선언
        List< Map<String, String> >  list = new ArrayList<>();
        try{ String sql = "select * from bcategory";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                // ==================== 1. Map 활용 ============= //
                // - map 컬렉션/객체 선언
                Map<String,String> map = new HashMap<>();
                // - map 컬렉션/객체 엔트리 2개 추가 , 카테고리번호 , 카테고리이름
                map.put( "bcno" ,  String.valueOf( rs.getInt( "bcno" )  ) );
                map.put( "bcname" ,  String.valueOf( rs.getString( "bcname" )  ) );
                // - map 컬렉션/객체를 리스트/객체에 담기
                list.add( map );
                // ==================== 2. Dto 활용 ============= //
                /* BoardDto boardDto = new BoardDto();
                boardDto.setBcno( rs.getInt( "bcno" ) );
                boardDto.setBcname( rs.getString( "bcname" ) );
                list.add( boardDto ); */
            }
        }catch (Exception e ){ System.out.println(e); }
        return list; // 리스트 반환
    }
    // 2.
    public boolean bWrite( BoardDto boardDto) {
        System.out.println("BoardDao.bWrite");
        System.out.println("boardDto = " + boardDto);
        try {
            String sql ="insert into board( bcno , btitle , bcontent , no , bfile ) " +
                    " values( ? , ? , ? , ? , ? )";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong( 1, boardDto.getBcno() );
            ps.setString( 2 , boardDto.getBtitle() );
            ps.setString( 3 , boardDto.getBcontent() );
            ps.setLong( 4 ,  boardDto.getNo() );
            ps.setString( 5 , boardDto.getBfile() );
            int count = ps.executeUpdate();
            if( count == 1 )return true;
        }catch (Exception e ){   System.out.println(e); }
        return false;
    }

    // 3. 게시물 전체 조회 처리
    public List<BoardDto> bFindAll(){  System.out.println("BoardDao.bFindAll");
        List<BoardDto> list = new ArrayList<>();
        try{ String sql = "select * from board inner join member " +
                " on board.no = member.no;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                // 레코드 를 하나씩 조회해서 Dto vs Map 컬렉션
                BoardDto boardDto = BoardDto.builder()
                        .bno( rs.getInt("bno") )
                        .btitle( rs.getString("btitle") )
                        .id( rs.getString("id") )
                        .bdate( rs.getString("bdate") )
                        .bview( rs.getInt("bview") )
                        .build();
                list.add( boardDto );
            }
        }catch (Exception e ){  System.out.println(e); }
        return list;
    }

    // 4. 게시물 개별 조회 처리
    public BoardDto bFindBno( int bno ){ System.out.println("BoardDao.bFindBno");System.out.println("bno = " + bno);
        try{
            String sql = "select bc.bcno , bcname, " +
                    " bno , btitle  , bcontent , " +
                    "        id , bdate , bview , bfile " +
                    " from board b " +
                    " inner join member m " +
                    "            inner join bcategory bc " +
                    " on b.no = m.no and b.bcno = bc.bcno " +
                    " where b.bno = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt( 1 , bno );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                // 레코드 를 하나씩 조회해서 Dto vs Map 컬렉션
                BoardDto boardDto = BoardDto.builder()
                        .bcno( rs.getInt("bcno") )
                        .bno ( rs.getInt("bno"))
                        .bcname( rs.getString("bcname"))
                        .btitle( rs.getString("btitle"))
                        .bcontent( rs.getString("bcontent"))
                        .id( rs.getString("id"))
                        .bdate( rs.getString("bdate"))
                        .bview( rs.getInt("bview"))
                        .bfile( rs.getString("bfile"))
                        .build();
                return boardDto;
            }
        }catch (Exception e ){  System.out.println(e); }
        return null;
    }


}



















