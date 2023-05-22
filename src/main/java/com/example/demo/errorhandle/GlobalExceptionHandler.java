package com.example.demo.errorhandle;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
@ControllerAdvice
public class GlobalExceptionHandler {
//	@ExceptionHandler(value=MaxUploadSizeExceededException.class)
//	public ModelAndView imageMaxSizeHandler() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("errorMsg", "圖片太大");
//		mav.setViewName("photo/uploadPage");
//		return mav;
//	}
	
	@ExceptionHandler(value= MaxUploadSizeExceededException.class)
	public String imageMaxSizeHandler(Model model) {
		model.addAttribute("errorMsg", "圖片太大www");
		return "photo/uploadPage";
	}

}
