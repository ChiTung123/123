package com.example.demo.medel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("from Customer where name = ?1 ")
	public List<Customer> findByName1(String name);
	
	@Query("from Customer where name = :n ")
	public List<Customer> findByName2(@Param(value="n") String name);
	
	@Query("from Customer where name like %:n% ")
	public List<Customer> findByNameLike(@Param(value="n") String name);
	
	@Query(value="select * from Customer where name = ?1",nativeQuery=true)
	public List<Customer> findByName3(String name);
	
	@Query(value="select * from Customer where name = :n",nativeQuery=true)
	public List<Customer> findByName4(@Param(value="n") String name);
	
	@Query("from Customer where level = ?1 ")
	public List<Customer> findByLevel1(int level);
	
	@Query("from Customer where level = :l ")
	public List<Customer> findByLevel2(@Param(value="l") int level);
	
	@Transactional
	@Modifying
	@Query(value="update Customer set name = :n where id = :id") //HQL
	public Integer updateNameById1(@Param("id") Integer id, @Param("n") String newName);
	
	public List<Customer> findByLevelOrderByIdDesc(int level);

}