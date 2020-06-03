<%@page import="kr.co.jsp.scores.model.Scores"%>
<%@page import="kr.co.jsp.scores.model.ScoreDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
	    request.setCharacterEncoding("utf-8");
	    
    	Scores scores = new Scores();
    	
    	scores.setName(request.getParameter("name"));
        scores.setKor(Integer.parseInt(request.getParameter("kor")));
        scores.setEng(Integer.parseInt(request.getParameter("eng")));
        scores.setMath(Integer.parseInt(request.getParameter("math")));
        scores.setTotal();
        scores.setAverage();
    
        
	    ScoreDAO dao = ScoreDAO.getInstance();
	    boolean flag = dao.insert(scores);
    
    	if(flag) {
    		response.sendRedirect("insert_succcess.jsp");
    	} else {
    		response.sendRedirect("insert_form.jsp");
    	}
    
    
    %>
    
    