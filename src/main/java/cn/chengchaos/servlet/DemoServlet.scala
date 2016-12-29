package cn.chengchaos.servlet

import java.util.Date
import javax.servlet.http._;

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

    out.println("结束 Servlet 的时间: " + new Date + ". ")
    out.flush()
    ctx.complete()
    
  }
  
}