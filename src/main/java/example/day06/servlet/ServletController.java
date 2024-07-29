package example.day06.servlet;
/*

*/

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// http://서버ip:서버port/day06/servlet  , http://localhost:8080/day06/servlet , http://192.168.30.9:8080/day06/servlet
@WebServlet("/day06/servlet")
public class ServletController extends HttpServlet {
    // extends : 상속 ( 해당 클래스에 특정 클래스로부터 상속받으면 해당 클래스는 특정클래스로부터 모든 필드/메소드 사용할수 있다.)
        // -  @Override : 오버라이딩 : 상속받은 클래스의 메소드를 재정의

    // 0. init()
    @Override
    public void init() throws ServletException {
        System.out.println("ServletController.init");
        System.out.println(">> 해당 클래스의 서블릿 객체가 생성되었다.");
        super.init();
    }
    // 0. service()
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.service");
        System.out.println(">> 해당 클래스의 서블릿 객체 서비스가 실행 되었다. ");
        super.service(req, resp);
    }
    // 0.
    @Override
    public void destroy() {
        System.out.println("ServletController.destroy");
        System.out.println(">> 해당 클래스의 서블릿 객체가 초기화 되었다. ");
        super.destroy();
    }

    // 1. doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doGet");
        System.out.println(">> HTTP의 get 메소드 방식으로 요청이 들어왔습니다. ");
        //super.doGet(req, resp); // super.메소드() : 부모클래스 메소드 호출
        // - 요청데이터 : 매개변수처럼 HTTP 요청시 들어오는 데이터 , HTTP 요청정보 관련 객체 : HttpServletRequest
        // http://localhost:8080/day06/servlet?data=serverHi
        System.out.println( "request Data : " + req.getParameter("data") );
        // - 응답데이터 : 리턴값처럼 HTTP 응답시 반환하는 데이터 , HTTP 응답정보 관련 객체 : HttpServletResponse
        resp.getWriter().print("response Data : [get]clientHi");
    }
    // 2. doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPost");
        System.out.println(">> HTTP의 post 메소드 방식으로 요청이 들어왔습니다. ");
        //super.doPost(req, resp);
        System.out.println("request Data : " + req.getParameter("data") ); // 1. 요청 데이터
        resp.getWriter().print("response Data : [post]clientHi");        // 2. 응답 데이터
    }
    // 3. doPut
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPut");
        System.out.println(">> HTTP의 put 메소드 방식으로 요청이 들어왔습니다. ");
        System.out.println("request Data : " + req.getParameter("data") );
        resp.getWriter().print("response Data : [put]clientHi");
    }
    // 4. doDelete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doDelete");
        System.out.println(">> HTTP의 delete 메소드 방식으로 요청이 들어왔습니다.");
        System.out.println("request Data : " + req.getParameter("data") );
        resp.getWriter().print("response Data : [delete]clientHi");
    }

}
