package ex01_lifecycle;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 * 
 *  * 서블릿이란?
 * 1. 웹 브라우저를 통해서 실행되는 Java 클래스이다.
 * 2. 클라이언트의 요청을 받을 수 있는 Java 클래스이다.
 * 3. javax.serlet.http.HttpServlet 클래스를 상속 받는다.
 * 4. jsp/servlet container(톰캣)에 저장되기 때문에 동일한 이름의 서블릿을 2개 이상 만들 수 없다.
 *     (톰캣이 없으면 추가해도 저장이 안됨, 톰캣에 의해 실행됨)
 * 5. 응답을 만들어서(태그를 만든다) 클라이언트에게 전송할 수 있다.
 */

/*
  URL
  
  1. 형식
    protocol://host:port/ContextPath/URLMapping
  2. ContextPath
    1) 프로젝트가 실행될 때 가지는 기본 경로를 의미한다. (Context Root)
    2) 수정 방법
      프로젝트 속성(properties) - Web Project Settings
  3. URl Mapping
    1) 서블릿의 실행 경로를 의미한다. (URL Pattern)
    2) 수정 방법
      (1) @WebServerlet 애너테이션 -> @WebServlet("/HelloServlet") 바로 밑 괄호 내용 수정
      (2) 프로젝트 src/main/webapp/WEB-INF/web.xml
    
 */



@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 1. 생성자
	 *   1) 서블릿 생성할 때 호출된다. (가장 먼저 호출된다.)
	 *   2) 서블릿 생성 후 자동으로 init() 메소드가 호출된다.
	 * 
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("1. HelloServlet() 생성자 호출");
    }

	/**
	 * 2. init()
	 *   1) 서블릿 환경 설정을 담당하는 메소드이다.
	 *   2) init() 메소드 호출 뒤 자동으로 service() 메소드가 호출된다.
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	  System.out.println("2. init() 메소드 호출");
	}



	/**
	 * 3. service() 메소드
	 *   1) 클라이언트가 요청할 때 자동으로 호출되는 메소드이다.
	 *   2) 클라이언트(html)로부터 요청을 받을 수 있고, 클라이언트에게 응답할 수 있다.
	 *   3) 요청을 처리하는 클래스 : javax.servlet.http.HttpServletRequest
	 *   4) 응답을 처리하는 클래스 : javax.servlet.http.HttpServletResponse
	 *       -> 서비스 파라미터로 되어있음.. ㅎㅎ 밑에서 확인 가능
	 *   5) service() 메소드가 없으면 대체로 doGet() 또는 doPost()와 같은 메소드가 요청 메소드에 따라서 자동으로 호출된다.
	 *       -> 요청메소드 $.ajax({type: 'get'}) -> doGet이 받아서 처리하는 것임! post면 doPost
	 *     
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  System.out.println("3. service() 메소드 호출");
	  // 요청 메소드에 따라서 doGet() 또는 doPost() 메소드 호출하기
	  switch(request.getMethod()) {
	  case "GET"  : doGet(request, response); break;
	  case "POST" : doPost(request, response); break;
	  }
	}

	/**
	 * 4. doGet()
	 *   1) GET 방식의 요청을 처리하는 메소드이다.
	 *   2) 기본적으로 POST 방식의 요청도 처리하도록 구성되어 있다.
	 *   3) GET 방식으로 요청하는 방법 / doGet 호출하는 방법
	 *    (1) <a href="/servlet/HelloServlet">   -> 이서블릿의 doGET 이 열림
	 *    (2) <form method="get" action="/servlet/HelloServlet">
	 *    (3) 자바스립트 location.herf='/servlet/HelloServlet'
	 *    (4) 자바스립트 window.open('/servlet/HelloServlet', '', '')
	 *    (5) $.ajax({
	 *          type : 'get',
	 *          url: '/servlet/HelloServlet',
	 *          ...
	 *          })
	 *    
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  System.out.println("4. doGet() 메소드 호출");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}	
	
	/**
	 * 5. doPost()
	 *   1) POST 방식의 요청을 처리하는 메소드이다.
	 *   2) 기본적으로 POST 방식의 요청이 오더라도 doGet()으로 처리를 이관한다.
	 *   3) POST 방식으로 요청하는 방법
	 *     (1) <form method="post" action="/servlet/HelloServlet">
	 *     (2) $.ajax({
   *          type : 'post',
   *          url: '/servlet/HelloServlet',
   *          ...
   *          })
   *          
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  System.out.println("4. doPost() 메소드 호출");
		doGet(request, response);
	}
	


}
