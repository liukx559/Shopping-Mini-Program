package com.atkexin.ssyx.common.exception;
import com.atkexin.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody//解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }
    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
//    @ExceptionHandler(SsyxException.class)
//    @ResponseBody
//    public Result error(SsyxException e){
//        return Result.build(null,e.getCode(), e.getMessage());
//    }
}