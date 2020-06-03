<%@page import="kr.co.jsp.scores.model.Scores"%>
<%@page import="kr.co.jsp.scores.model.ScoreDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    	파라미터 데이터 얻어오신 후 DAO에게 삭제 요청해 주시면 됩니다.
    	(주소값 받아서 메서드 호출.)
    	public boolean delete(???)
    	
    	삭제가 완료되면 score_list.jsp로 이동.
    	삭제 실패해도 score_list.jsp로 이동.
    	삭제가 성공했는지 실패했는지를 script태그를 이용하여
    	경고창으로 출력해 주세요.
    	location.href="URL" -> 이걸로 이동시키기.
    	*/
    	
		int id = Integer.parseInt(request.getParameter("id"));
    	
    	Boolean flag = ScoreDAO.getInstance().delete(id); 
    
    	if(flag) { %>
    		<script>
    			alert("삭제가 정상 처리되었습니다.")
    			location.href="score_list.jsp";
    		</script>
    	<% } else { %>
    		<script>
    			alert("삭제에 실패했습니다.")
    			location.href="score_list.jsp";
    		</script>
    	<% } %>
    	