package com.mihigo.main;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.Users;
import com.mihigo.main.repositories.SiteRepository;
import com.mihigo.main.repositories.UserRepository;

@SpringBootTest
class KigaliApplicationTests {

	@Autowired
	private SiteRepository sire;
	
	@Autowired
	private UserRepository ur;
	
	@Test
	void contextLoads() {
		
		List<Users> lu=ur.allUserBySiteRefKey("ieMBeOVAtKr4Niar");
		
		lu.forEach(x->{
			System.out.println(x.getUsername());
		});
	}

}
