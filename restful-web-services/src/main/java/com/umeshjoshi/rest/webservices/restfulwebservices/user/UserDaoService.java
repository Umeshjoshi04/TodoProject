package com.umeshjoshi.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;


@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static int count=0;

	static {
		users.add(new User(++count, "Umesh", LocalDate.now().minusYears(25)));
		users.add(new User(++count, "Nirmal", LocalDate.now().minusYears(23)));
		users.add(new User(++count, "Joshi", LocalDate.now().minusYears(30)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
//		users.stream().forEach(user-> {user.getId().equals(id)}).user;
	}

	public User save(User user) {
		user.setId(++count);
		users.add(user);
		return user;		
	}

}
