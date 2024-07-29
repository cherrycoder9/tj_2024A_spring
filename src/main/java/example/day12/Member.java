package example.day12;

public class Member {

    int memberId;
    String memberName;

    // 중복 기준 정의 , 회원번호로 중복 제거
    @Override
    public int hashCode() {
        return memberId;
    }
    //
    @Override
    public boolean equals(Object obj) {
        if( obj instanceof Member ){
            Member member = (Member)obj;
            if( this.memberId == member.memberId ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public Member(int memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
