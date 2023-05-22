package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.medel.Photo;
import com.example.demo.service.PhotoService;

@Controller
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/photo/upload")
	public String uploadPhoto() {
		return "photo/uploadPage";
	}

	@PostMapping("/photo/uploadPost")
	public String uploadPost(@RequestParam("photoName") String photoName, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		try {
			Photo gp = new Photo();
			gp.setPhotoName(photoName);
			gp.setPhotoFile(file.getBytes());

			photoService.insertPhoto(gp);

			redirectAttributes.addFlashAttribute("ok", "上傳成功");
			return "redirect:/photo/upload";
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "上傳失敗");
			return "redirect:/photo/upload";
		}
	}
}