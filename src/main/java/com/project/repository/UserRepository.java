//We have created UserRepository inside com.project which contains @SpringBootApplication implementation
// so we do not need to create any service here to implement UserRepository

package com.project.repository;
import org.springframework.data.jpa.repository.*;
//import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.project.entities.User;



public interface UserRepository extends JpaRepository<User,Integer>{
	
}