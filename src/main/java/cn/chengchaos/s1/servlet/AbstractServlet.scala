package cn.chengchaos.s1.servlet

import javax.servlet.http._;
import javax.servlet.annotation._;

//@WebServlet(urlPatterns=Array{"/demo"}, asyncSupported=true)
abstract class AbstractServlet extends HttpServlet {
  
  protected def perform(req: HttpServletRequest, resp: HttpServletResponse)
  
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) 
    = perform(req, resp);
  
  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) 
    = perform(req, resp);
}