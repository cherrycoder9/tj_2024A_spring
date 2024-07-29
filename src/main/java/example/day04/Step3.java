package example.day04;

public class Step3 {
    public static void main(String[] args) {

        // 문제 : money 변수의 존재하는 문자열 금액의 천단위 쉼표를 넣기
            // 천단위 쉼표 가 포함된 형식으로 반환하는 함수 구현 하시오.
        String money = "0123456789";
        System.out.println(  천단위변환( money ) ) ;
        System.out.println(  천단위변환( "4a6443454" ) ) ;
    }

    static String 천단위변환( String money ){
        String result = "";
        for( int i = 0 ; i < money.length() ; i++ ){ // - i는 0부터 입력받은문자길이 까지 1씩 증가 반복
            if( i > 0 && ( money.length() - i ) % 3 == 0  ){ // 뒤에서부터 자릿수가 3의 배수이면 //  배수찾기 : 값%배수 == 0 , 나머지가 0 이면 값은 그 배수
                result += ","; // 천단위 쉼표를 result 에 대입
            }
            result += money.charAt(i);
            System.out.println( (int)money.charAt(i));
        }
        return  result;
    }

}
