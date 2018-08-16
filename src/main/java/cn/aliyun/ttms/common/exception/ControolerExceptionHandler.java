package cn.aliyun.ttms.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.aliyun.ttms.common.web.JsonResult;



/**此注解用于标识此类为全局的异常处理类*/
@ControllerAdvice
public class ControolerExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult handleException(Exception e){
		//各种异常
		System.out.println("exception");
		return new JsonResult(e);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult handleException(RuntimeException e){
		//运行期异常
		System.out.println("runtime exception");
		return new JsonResult(e);
	}
	
}













