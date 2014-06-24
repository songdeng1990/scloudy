<%@tag pageEncoding="utf-8" isELIgnored="false" description="公用头部" body-content="empty"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.0.min.js"></script>

<link href="${pageContext.request.contextPath}/resources/messager/messenger.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/messager/messenger-theme-future.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/messager/messenger.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/messager/messagerUtil.js"></script>

<link href="${pageContext.request.contextPath}/resources/sco/scojs.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sco/sco.modal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sco/sco.confirm.js"></script>
	
<script type="text/javascript" >
//var $j = $.noConflict();
</script>