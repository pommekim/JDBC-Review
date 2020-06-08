<%@page import="kr.co.jsp.board.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	int id = Integer.parseInt(request.getParameter("bId"));
    
    	if(BoardDAO.getInstance().delete(id)) { %>
    		<script>
    			alert("삭제가 정상 처리되었습니다.");
    			location.href="list.jsp";
    		</script>
    	<% } else {  %>
    		<script>
    			alert("삭제 실패!");
    			location.href="list.jsp";
    		</script>
    	<% } %>
 
    
    
    
    
    
    
    
    
    