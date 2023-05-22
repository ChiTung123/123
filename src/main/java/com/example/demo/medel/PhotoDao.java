package com.example.demo.medel;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;

@Repository
public class PhotoDao {
	
	@PersistenceContext
	private Session session;
	
	public Photo getPhotoById(Integer id) {
		 return session.get(Photo.class, id);
	}
	
	// ... hibernate 方法

}