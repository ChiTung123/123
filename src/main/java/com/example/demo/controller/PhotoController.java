package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/photo/image")
	public ResponseEntity<byte[]> getImage(@RequestParam("id") Integer id){
		Photo gp = photoService.findPhotoById(id);
		byte[] photoFile = gp.getPhotoFile();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_JPEG);
		                                 // 回傳值 , header, status
		return new ResponseEntity<byte[]>(photoFile, header, HttpStatus.OK);
	}
	
	@GetMapping("/photo/list")
	public String listPhotoData(Model model) {
		List<Photo> gpList = photoService.listAllPhoto();
		model.addAttribute("photoList", gpList);
		return "photo/listPhoto";
	}
}