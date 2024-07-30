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
    public List<Map<String, String>> bcFindAll() {
        // public List< BoardDto > bcFindAll( ) {
        System.out.println("BoardDao.bcFindAll");
        // - list 컬렉션 선언 , map컬렉션(객체) 을 여러개 저장하기 위해 list 선언
        final List<Map<String, String>> list = new ArrayList<>();
        try {
            final String sql = "select * from bcategory";
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // ==================== 1. Map 활용 ============= //
                // - map 컬렉션/객체 선언
                final Map<String, String> map = new HashMap<>();
                // - map 컬렉션/객체 엔트리 2개 추가 , 카테고리번호 , 카테고리이름
                map.put("bcno", String.valueOf(rs.getInt("bcno")));
                map.put("bcname", String.valueOf(rs.getString("bcname")));
                // - map 컬렉션/객체를 리스트/객체에 담기
                list.add(map);
                // ==================== 2. Dto 활용 ============= //
                /* BoardDto boardDto = new BoardDto();
                boardDto.setBcno( rs.getInt( "bcno" ) );
                boardDto.setBcname( rs.getString( "bcname" ) );
                list.add( boardDto ); */
            }
        } catch (final Exception e) {
            System.out.println(e);
        }
        return list; // 리스트 반환
    }

    // 2.
    public boolean bWrite(final BoardDto boardDto) {
        System.out.println("BoardDao.bWrite");
        System.out.println("boardDto = " + boardDto);
        try {
            final String sql = "insert into board( bcno , btitle , bcontent , no , bfile ) " +
                    " values( ? , ? , ? , ? , ? )";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, boardDto.getBcno());
            ps.setString(2, boardDto.getBtitle());
            ps.setString(3, boardDto.getBcontent());
            ps.setLong(4, boardDto.getNo());
            ps.setString(5, boardDto.getBfile());
            final int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (final Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 3-2. 전체 게시물수 반환 처리
    public int getTotalBoardSize(final int bcno) {
        try {
            String sql = "select count(*) as 총게시물수 from board ";
            // 카테고리가 존재하면, 0이면 카테고리가 없다는 의미, 1이상이면 카테고리의 pk번호
            if (bcno >= 1) {
                sql += " where bcno = " + bcno;
            }
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // 총게시물수
            }

        } catch (final Exception e) {
            System.out.println("e = " + e);
        }
        return 0;
    }

    // 3. 게시물 전체 조회 처리
    public List<BoardDto> bFindAll(final int startRow, final int pageBoardSize, final int bcno) {
        System.out.println("BoardDao.bFindAll");
        final List<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board inner join member on board.no = member.no ";

            if (bcno >= 1) {
                sql += "where bcno = " + bcno;

            }
            // 5. 정렬 조건, 내림차순
            sql += " order by board.bno desc ";

            // 6. 레코드 제한, 페이징처리
            sql += " limit ?, ?";

            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, startRow);
            ps.setInt(2, pageBoardSize);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // 레코드 를 하나씩 조회해서 Dto vs Map 컬렉션
                final BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .build();
                list.add(boardDto);
            }
        } catch (final Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // 4. 게시물 개별 조회 처리
    public BoardDto bFindBno(final int bno) {
        System.out.println("BoardDao.bFindBno");
        System.out.println("bno = " + bno);
        try {
            final String sql = "select bc.bcno , bcname, " +
                    " bno , btitle  , bcontent , " +
                    "        id , bdate , bview , bfile " +
                    " from board b " +
                    " inner join member m " +
                    "            inner join bcategory bc " +
                    " on b.no = m.no and b.bcno = bc.bcno " +
                    " where b.bno = ? ";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // 레코드 를 하나씩 조회해서 Dto vs Map 컬렉션
                final BoardDto boardDto = BoardDto.builder()
                        .bcno(rs.getInt("bcno"))
                        .bno(rs.getInt("bno"))
                        .bcname(rs.getString("bcname"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .bfile(rs.getString("bfile"))
                        .build();
                return boardDto;
            }
        } catch (final Exception e) {
            System.out.println(e);
        }
        return null;
    }


}



















