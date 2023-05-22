package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.medel.Message;
import com.example.demo.medel.MessageRepository;


@Service
public class MessageService {
	
	@Autowired
	private MessageRepository mDao;
	
	public void insertMessage(Message msg) {
		mDao.save(msg);
	}
	
	public Message findMessageById(Integer id) {
		Optional<Message> option = mDao.findById(id);
		
		if(option.isPresent()) {
			return option.get();
		}
		
		System.out.println("沒找到");
		return null;
	}
	
	@Transactional
	public void deleteById(Integer id) {
		mDao.deleteById(id);
	}

	public Message getLatestMessage() {
//		return mDao.findFirstByOrderByAddedDesc();
		return mDao.findFirst();
	}

	public Page<Message> findByPage(Integer pageNumber){
		PageRequest pgb = PageRequest.of(pageNumber-1, 3, Sort.Direction.DESC, "added");
		Page<Message> page = mDao.findAll(pgb);
		return page;
	}
	
	@Transactional
	public Message updateMsgById(Integer id, String newMessage) {
		Optional<Message> option = mDao.findById(id);
		
		if(option.isPresent()) {
			Message messages = option.get();
			messages.setText(newMessage);
			return messages;
		}
		
		System.out.println("沒有修改資料");
		return null;
	}

}
