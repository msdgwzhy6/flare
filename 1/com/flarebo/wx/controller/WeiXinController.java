package com.flarebo.wx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flarebo.wx.service.WeiXinService;
import com.flarebo.wx.util.SignUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2014/9/7.
 */
@Controller
@RequestMapping("/flarebo")
public class WeiXinController {
	@RequestMapping(method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (SignUtil.checkSignature(signature, timestamp, nonce))
				out.print(echostr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			out=null;
			}
		
	}
@RequestMapping(method=RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String respMessage=WeiXinService.processRequest(request);
		out.print(respMessage);
		out.close();
	}
}
