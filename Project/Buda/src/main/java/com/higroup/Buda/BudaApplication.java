package com.higroup.Buda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BudaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudaApplication.class, args);
	}
	
	// @Bean
	// public CommandLineRunner addRole(RoleRepository repo, UserRepository userRepository, StaffRepository staffRepository){
	// 	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	// 	return args -> {
	// 		try{
	// 			repo.save(new Role("ADMIN"));
	// 			repo.save(new Role("MODERATOR"));
	// 			repo.save(new Role("USER"));
	// 			repo.save(new Role("STAFF"));
	// 		}
	// 		catch (Exception e){
				
	// 		}
	// 		Role role = repo.findRoleByName("USER").get();
	// 		User user = new User();
	// 		user.setUserName("nguyenhoangvudtm23");
	// 		user.password(bCryptPasswordEncoder.encode("123456789"));
	// 		user.setEmail("nguyenhoangvudtm23@gmail.com");
	// 		user.setFirstName("Vu");
	// 		user.setLastName("Nguyen Hoang");
	// 		user.setPhoneNumber("0367185116");
			
	// 		user.addRole(role);
	// 		userRepository.save(user);
			
	// 		Role role_staff = repo.findRoleByName("STAFF").get();
	// 		Staff staff = new Staff();
	// 		staff.setName("tran quang hai");
	// 		staff.password(bCryptPasswordEncoder.encode("123456789"));
	// 		staff.setPhoneNumber("0972787125");
	// 		staff.setAccount("toilahai");
	// 		staff.setEmail("tranquanghai@gmail.com");
	// 		staff.loginID("XXXXXXXX");
	// 		staff.salary(20.0);
	// 		staff.address("Ha Noi");
	// 		staff.userID(user.getUserID());
	// 		staff.addRole(role_staff);
	// 		staff.staffPosition(StaffPosition.MANAGER);
	// 		staffRepository.save(staff);
	// 	};
	// } 
}
