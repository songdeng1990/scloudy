package com.redis.monitor.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.RedisJedisPool;
import com.redis.monitor.RedisServer;

@Controller
@RequestMapping(value="/server")
public class RedisServerController extends BaseProfileController {
	
	@RequestMapping(value="/redisServer.htm")
	public String toRedisServer() {
		return "redisServer";
	}
	
	@RequestMapping(value="/redisServerList.htm",method=RequestMethod.POST)
	public ModelAndView redisServerList() {
		ModelAndView mv = getJsonModelAndView();
		List<RedisServer> list = RedisJedisPool.getAllRedisServer();;
		mv.addObject("rows", list);
		mv.addObject("total", list == null ? 0 : list.size());
		return mv;
	}
	
	@RequestMapping(value="/newServer.htm",method=RequestMethod.POST)
	public String addRedisServer(HttpServletRequest request,@ModelAttribute RedisServer redisServer) {
		RedisJedisPool.removeRedisServer(redisServer.getUuid());
		RedisJedisPool.addNewRedisServer(redisServer);
		//TODO redis列表加载
		List<RedisServer> rsList = redisManager.redisServerList();
		request.setAttribute("redisServerList", rsList);
		return "redisServer";
	}
	
	@RequestMapping(value="/removeServer.htm",method=RequestMethod.GET)
	public ModelAndView removeRedisServer(HttpServletRequest request,@RequestParam(required=true) String uuid) {
		RedisJedisPool.removeRedisServer(uuid);
		//TODO redis列表加载
		List<RedisServer> rsList = redisManager.redisServerList();
		request.setAttribute("redisServerList", rsList);
		ModelAndView mv = getJsonModelAndView();
		List<RedisServer> list = RedisJedisPool.getAllRedisServer();;
		mv.addObject("rows", list);
		mv.addObject("total", list == null ? 0 : list.size());
		return mv;
	}
}
