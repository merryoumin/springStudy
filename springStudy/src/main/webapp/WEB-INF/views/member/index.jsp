<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
menu_gubun : ${menu_gubun}<br>
<%@ include file="../include/inc_header.jsp" %>   
pageNumber : <span id="span_pageNumber">${pageNumber}</span><br>
no : <span id="span_no">${no}</span><br>
proc : <span id="span_proc">${proc}</span><br>
search_option : <span id="span_search_option">${search_option}</span><br>
search_data : <span id="span_search_data">${search_data}</span><br>
path :<span id="span_path">${path}</span><br><br>
arg01 :<span id="span_arg01">${arg01}</span><br><br>
<input type="text" name="a" style="display: ;"><br>

<div id ="result" style="border: 1px solid red; height: 70%;"></div>
<script src='<c:url value="/resources/js/_member.js"/>'>
</script>
