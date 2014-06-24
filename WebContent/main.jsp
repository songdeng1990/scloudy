<%@page import="com.redis.monitor.RedisJedisPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String uuid = RedisJedisPool.getAllRedisServer().get(0).getUuid();

response.sendRedirect(basePath + "index.htm?uuid=" + uuid);
%>
