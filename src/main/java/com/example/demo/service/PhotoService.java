package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.medel.Photo;
import com.example.demo.medel.PhotoRepository;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepository photoRepository;

	public Photo insertPhoto(Photo gp) {
		return photoRepository.save(gp);
	}

	public Photo findPhotoById(Integer id) {
		Optional<Photo> option = photoRepository.findById(id);

		if (option.isPresent()) {
			Photo resultPhoto = option.get();
			return resultPhoto;
		}
		return null;
	}

	public List<Photo> listAllPhoto() {
		return photoRepository.findAll();
	}
}