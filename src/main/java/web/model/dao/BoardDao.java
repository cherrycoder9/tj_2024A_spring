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
    public int getTotalBoardSize(final int bcno, final String searchKey, final String searchKeyword) {
        try {
            String sql = "select count(*) as 총게시물수 from board inner join member on board.no = member.no ";

            // 카테고리가 존재하면, 0이면 카테고리가 없다는 의미, 1이상이면 카테고리의 pk번호
            if (bcno >= 1) {
                sql += " where bcno = " + bcno;
            }

            // 검색이 존재 했을때, keyword가 존재하면
            if (searchKeyword.isEmpty()) { // 문자열이 비어 있으면, 검색이 없다라는 의미의 뜻으로 활용

            } else { // 비어있지 않으면, 검색이 있다라는 의미로 활용
                if (bcno >= 1) {
                    // 카테고리가 있을때는 and 추가
                    sql += " and ";
                } else {
                    // 카테고리가 없을때(전체보기)는 where 추가
                    sql += " where ";
                }
                // 검색 sql
                sql += searchKey + " like '%" + searchKeyword + "%'";
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
    public List<BoardDto> bFindAll(final int startRow, final int pageBoardSize, final int bcno, final String searchKey, final String searchKeyword) {
        System.out.println("BoardDao.bFindAll");
        final List<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board inner join member on board.no = member.no ";

            // 4. 일반조건 1
            // 전체보기이면 where 절 생략, bcno = 0이면
            // 카테고리명 보기이면 where 절 추가, bcno >= 1이면
            if (bcno >= 1) {
                sql += "where bcno = " + bcno;

            }

            // 4. 일반조건 2
            if (searchKeyword.isEmpty()) {
                System.out.println("searchKeyword.isEmpty()");
            } else {
                if (bcno >= 1) {
                    // 카테고리가 있을때는 and 추가
                    sql += " and ";
                } else {
                    // 카테고리가 없을때(전체보기)는 where 추가
                    sql += " where ";
                }
                // 검색 sql
                sql += searchKey + " like '%" + searchKeyword + "%'";
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

    // 5. 조회수 증가 처리
    public void bViewIncrease(final int bno) {
        System.out.println("BoardDao.bViewIncrease");
        try {
            final String sql = "update board set bview = bview + 1 where bno = ? ";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            ps.executeUpdate();


        } catch (final Exception e) {
            System.out.println(e);
        }
    }

    // 5. 게시물의 댓글 쓰기 처리
    public boolean bReplyWrite(final Map<String, String> map) {
        System.out.println("BoardDao.bReplyWrite");
        System.out.println("map = " + map);
        // ??? 왜 map
        // 여러 데이터를 유연하게 처리하기 위해 사용
        // 다양한 필드를 받아야 할 때 유용

        try {
            final String sql = "insert into breply(brindex, brcontent, no, bno) values(?,?,?,?)";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(map.get("brindex"))); // ??? 왜 parseInt 하는지
            // Map<String, String>으로 받은 데이터는 문자열 타입이기 때문
            ps.setString(2, map.get("brcontent"));
            ps.setInt(3, Integer.parseInt(map.get("no")));
            ps.setInt(4, Integer.parseInt(map.get("bno")));

            final int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (final Exception e) {
            System.out.println(e);
        }
        return false; // ??? 왜 true/false를 넣는건지
        // 댓글 쓰기 작업의 성공 여부를 나타내기 위해
    }

    // 6. 게시물에 달린 댓글 출력 처리 Dao (Map 자료형 사용)
    public List<Map<String, String>> bReplyList(final int bno) {
        System.out.println("BoardDao.bReplyList");
        try {
            final String sql = "SELECT br.*, m.name " +
                    "FROM breply br " +
                    "JOIN member m ON br.no = m.no " +
                    "WHERE br.bno = ? " +
                    "ORDER BY br.brindex DESC";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            final ResultSet rs = ps.executeQuery();

            final List<Map<String, String>> list = new ArrayList<>();

            while (rs.next()) {
                final Map<String, String> map = new HashMap<>();
                map.put("brdate", rs.getString("brdate"));
                map.put("brcontent", rs.getString("brcontent"));
                map.put("name", rs.getString("name")); // 댓글 작성자의 이름 추가
                list.add(map);
            }
            return list;
        } catch (final Exception e) {
            System.out.println(e);
        }
        return null;
    }

}



















