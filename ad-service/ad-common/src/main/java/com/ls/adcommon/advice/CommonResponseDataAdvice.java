package com.ls.adcommon.advice;

import com.ls.adcommon.annotation.IgnoreResponseAdvice;
import com.ls.adcommon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author lijiayin
 */
@RestControllerAdvice
@SuppressWarnings("all")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    
    @Override
    public boolean supports(MethodParameter returnType, 
                            Class<? extends HttpMessageConverter<?>> converterType) {
        
        if(returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        
        if(returnType.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, 
                                  MethodParameter returnType, 
                                  MediaType selectedContentType, 
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, 
                                  ServerHttpRequest request, 
                                  ServerHttpResponse response) {
        CommonResponse<Object> commonResponse = new CommonResponse<>(0, "");
        if(null == body){
            return commonResponse;
        } else if (body instanceof CommonResponse){
            commonResponse = (CommonResponse<Object>) body;
        } else {
            commonResponse.setData(body);
        }
            
        return commonResponse;
    }
}
