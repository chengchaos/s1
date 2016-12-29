package controller;

import cn.chengchaos.controller.DemoController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by chengchao on 2016/12/29.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-servlet.xml"
})
public class DemoControllerTest {

    @Autowired
    private DemoController demoController;

    @Test
    public void test1() {
        System.out.println("hello");
    }

    @Test
    public void testController() {

        String result = this.demoController.demo();
        System.out.println(result);
        Assert.assertEquals("demo", result);

    }
}
