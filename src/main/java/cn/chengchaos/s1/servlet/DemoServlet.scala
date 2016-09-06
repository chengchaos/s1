package cn.chengchaos.s1.servlet

import javax.servlet.http._;
import javax.servlet.annotation._;

//@WebServlet(urlPatterns=Array{"/demo"}, asyncSupported=true)
class DemoServlet extends AbstractServlet {
  
  override protected def perform(req: HttpServletRequest, resp: HttpServletResponse)
    :Unit = {
    resp.setContentType("text/html; charset=UTF-8");
    val out = resp.getWriter()
    if (!req.isAsyncSupported()) {
      out.print("不支持异步");
      out.flush();
      return;
    }
    
    req.startAsync(req, resp);
    
    if (!req.isAsyncStarted) {
      out.print("不能启动异步");
      out.flush();
      return;
    }
    
    val ctx = req.getAsyncContext();
    
  }
  
}