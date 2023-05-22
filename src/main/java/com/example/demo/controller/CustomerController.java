package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.medel.Customer;
import com.example.demo.medel.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerDao;
	
	@PostMapping("/customer/add")
	public Customer insertCustomer() {
		Customer c1 = new Customer();
		c1.setName("館長");
		c1.setLevel(3);
		
		return customerDao.save(c1);
		
	}
	
	
	@PostMapping("/customer/add2")
	public Customer insertCustomer2(@RequestBody Customer reqC) {
		return customerDao.save(reqC);
	}

	@PostMapping("/customer/add3")
	public List<Customer> insertCustomer3(@RequestBody List<Customer> reqC) {
		return customerDao.saveAll(reqC);
	}
	
	@GetMapping("/customer/find1")
	public List<Customer> findCustomer() {
		return customerDao.findAll();
	}
	
	@GetMapping("/customer/{id}")
	public Customer getById(@PathVariable Integer id) {
		Optional<Customer> option = customerDao.findById(id);
		
		if(option.isPresent()) {
			Customer resCus = option.get();
			return resCus;
		}
		
		Customer err = new Customer();
		err.setName("沒有這筆資料");
		
		return err;	
	}
	
	@DeleteMapping("/customer/delete/{id}")
	public String deleteById1(@PathVariable Integer id) {
			Optional<Customer> option = customerDao.findById(id);
			if(option.isEmpty()) {
				return "刪除失敗";
			}
			customerDao.deleteById(id);
		return "刪除成功";
	}
	
	@DeleteMapping("/customer/delete")
	public String deleteById2(@RequestParam Integer id) {
		Optional<Customer> option = customerDao.findById(id);
		if(option.isEmpty()) {
			return "刪除失敗";
		}
		customerDao.deleteById(id);
	return "刪除成功";
	}
	@GetMapping("/customer/page")
	public List<Customer> findByPage(@RequestParam(name = "page") Integer pageNumber){
		PageRequest pgb = PageRequest.of(pageNumber-1, 2, Sort.Direction.ASC, "id");
		Page<Customer> page = customerDao.findAll(pgb);
		return page.getContent();
	}
	
	@GetMapping("/customer/q1")
	public List<Customer> testQuery1(@RequestParam("name") String name){
//		return customerDao.findByName1(name);
//		return customerDao.findByName2(name);
		return customerDao.findByName3(name);
//		return customerDao.findByName4(name);
	}	
	//對應到CustomerRepository介面
	
	
	@GetMapping("/customer/q2")
	public List<Customer> testQuery2(@RequestParam("level") int level){
//		return customerDao.findByLevel1(level);
		return customerDao.findByLevel2(level);
	}	
	
	@GetMapping("/customer/q3")
	public List<Customer> testQuery3(@RequestParam("level") int level){
//		return customerDao.findByLevel1(level);
		return customerDao.findByLevelOrderByIdDesc(level);
	}	
	
	
	
	@PutMapping("/customer/update")
	public Integer updateQuery(@RequestParam("id") Integer id, @RequestParam("name") String newName) {
		return customerDao.updateNameById1(id, newName);
	}
	
	@GetMapping("/customer/like1")
	public List<Customer> findCustomerNameLike(@RequestParam("name") String name) {
		return customerDao.findByNameLike(name);
	}
	
	
}