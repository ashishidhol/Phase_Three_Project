package com.ecommerce.webapp.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.webapp.entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	 
     
}
     



