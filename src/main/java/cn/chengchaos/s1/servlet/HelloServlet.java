package cn.chengchaos.s1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(asyncSupported = true, urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HelloServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setHeader("Cache-Control", "private");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Connection", "Keep-Alive");
		resp.setHeader("Proxy-Connection", "Keep-Alive");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("进入 Servlet 的时间: " + new Date() + ". ");
		LOGGER.info("进入 Servlet 的时间: " + new Date() + ". ");
		out.flush();

		LOGGER.info("准备进入异步调用线程");

		if (!req.isAsyncSupported()) {
			LOGGER.warn("the servlet is not supported Async");
			return;
		}

		AsyncContext ctx = req.startAsync(req, resp);
		// AsyncContext ctx = req.startAsync();

//		if (req.isAsyncStarted()) {
//			AsyncContext asyncContext = req.getAsyncContext();
//			asyncContext.setTimeout(1L * 60L * 1000L);// 60sec
//			new CounterThread(asyncContext).start();
//		} else {
//			LOGGER.error("the ruquest is not AsyncStarted !");
//		}

		/* 在子线程中执行业务调用, 并由其负责输出相应, 主线程退出 */
		if (req.isAsyncStarted()) {
			LOGGER.info("准备启动异步调用线程");
			new Thread(new Executor(ctx)).start();
			LOGGER.info("结束 Servlet 的时间: " + new Date() + ". ");
			out.println("结束 Servlet 的时间: " + new Date() + ". ");
			out.flush();
		} else {
			LOGGER.error("the ruquest is not AsyncStarted !");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	private static class Executor implements Runnable {

		private AsyncContext ctx = null;

		public Executor(AsyncContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			// 等 10 秒
			LOGGER.info("进入异步调用线程");
			try {
				for (int i = 1; i <= 10; i++) {
					Thread.sleep(1000);
					System.out.print(".." + i);
				}
				PrintWriter out = ctx.getResponse().getWriter();
				LOGGER.info("工作完成的时间: " + new Date() + ". ");
				out.println("工作完成的时间: " + new Date() + ". ");
				out.flush();
				ctx.complete();
				LOGGER.info("退出异步调用线程");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@SuppressWarnings("unused")
	private static class CounterThread extends Thread {
		
		private static final Logger log = LoggerFactory
				   .getLogger(CounterThread.class);
		
		private AsyncContext asyncContext;

		public CounterThread(AsyncContext asyncContext) {
			this.asyncContext = asyncContext;
		}

		@Override
		public void run() {
			int interval = 1000 * 3; // 3 sec

			try {
				log.info("now sleep 20s, just as do some big task ...");
				Thread.sleep(interval);
				log.info("now dispatch to another Async Servlet");

				ServletRequest request = asyncContext.getRequest();
				String disUrl = request.getParameter("disUrl");
				if (StringUtils.isBlank(disUrl)) {
					disUrl = "/hello";
				}

				if (disUrl.endsWith(".jsp")) {
					request.setAttribute("dateStr", DateFormatUtils.format(
							System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
				}

				log.info("disUrl is ： " + disUrl);

				// 将当前异步上下文所持有的request, response分发给Servlet容器
				if (StringUtils.equals("self", disUrl)) {
					// 将分发到自身，即当前异步请求地址
					asyncContext.dispatch();
				} else {
					// 将分发到指定的路径
					asyncContext.dispatch(disUrl);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
