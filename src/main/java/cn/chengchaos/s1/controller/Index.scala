package cn.chengchaos.s1.controller;

import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation._

@Controller
@RequestMapping
class IndexController {
  
  @RequestMapping( value = Array{"/index"}, method = Array{RequestMethod.GET})
  def index() : String = "index"
  
}