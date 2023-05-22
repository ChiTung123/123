package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.medel.Message;
import com.example.demo.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService mService;

	@GetMapping("/message/add")
	public String addMsgPage(Model model) {

//        Message latestMsg = mService.getLatestMessage();
//		model.addAttribute("latestMessage",latestMsg);
		return "message/addMessage";
	}

	@PostMapping("/message/post")
	public String addMsgPost(@RequestParam("text") String text, Model model) {

		Message msg = new Message();
		msg.setText(text);
//		msg.setAdded(new Date());

		mService.insertMessage(msg);

		Message latestMsg = mService.getLatestMessage();
		model.addAttribute("latestMessage", latestMsg);
		return "message/addMessage";
	}

	@GetMapping("/message/page")
	public String findByPage(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model model) {
		Page<Message> page = mService.findByPage(pageNumber);
		model.addAttribute("page", page);
		return "message/showMessages";
	}

	@GetMapping("/message/edit")
	public String editPage(@RequestParam("id") Integer id, Model model) {
		Message msg = mService.findMessageById(id);
		model.addAttribute("message", msg);
		return "message/editMessage";
	}

	@PutMapping("/message/put")
	public String editPut(@ModelAttribute("message") Message msg) {
		mService.updateMsgById(msg.getId(), msg.getText());
		return "redirect:/message/page";
	}

	@DeleteMapping("/message/delete")
	public String delPage(@RequestParam("id") Integer id) {
		mService.deleteById(id);
		return "redirect:/message/page";
	}
}
