package com.example.demo.medel;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("from Message where text = ?1 ")
	public List<Message> findByText1(String Text);

	@Query("from Message where text = :n ")
	public List<Message> findByText2(@Param(value = "n") String Text);

	@Query("from Message where text like %:n% ")
	public List<Message> findByTextLike(@Param(value = "n") String Text);

	@Query(value = "select * from Message where text = ?1", nativeQuery = true)
	public List<Message> findByText3(String Text);

	@Query(value = "select * from Message where text = :n", nativeQuery = true)
	public List<Message> findByText4(@Param(value = "n") String Text);

	@Query("from Message where added = ?1 ")
	public List<Message> findByAdded1(Date added);

	@Query("from Message where added = :l ")
	public List<Message> findByAdded2(@Param(value = "l") Date added);

	@Transactional
	@Modifying
	@Query(value = "update Message set text = :n where id = :id") // HQL
	public Integer updateTextById1(@Param("id") Integer id, @Param("n") String newText);

	public List<Message> findByAddedOrderByIdDesc(Date added);

	public Message findFirstByOrderByAddedDesc();

	@Query(value = "select top(1) * from message order by added Desc", nativeQuery = true)
	public Message findFirst();

}