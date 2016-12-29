package cn.chengchaos.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._

@Controller
@RequestMapping
class IndexController {
  
  @RequestMapping(value = Array{"/index"}, method = Array{RequestMethod.GET})
  def index(model: Model) : String = {

    model.addAttribute("name", "chengchao")
    "index"
  }
  
}