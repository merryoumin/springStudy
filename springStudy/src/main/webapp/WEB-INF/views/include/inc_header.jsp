<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:set var="url" value="${pageContext.request.requestURL }" />
<c:set var="uri" value="${pageContext.request.requestURI }" />
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>


<%
	 response.setHeader("Cache_Control","no_cache");
	 response.setHeader("Pragma","no_cache");
	 response.setDateHeader("Expires",0);
%>