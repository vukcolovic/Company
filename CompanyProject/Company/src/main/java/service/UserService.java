package service;

import java.util.List;

import javax.ejb.Remote;

import model.User;

@Remote
public interface UserService {
	
	List<User> getAllUsers();
	
	User getUserById(Integer id);
	
	User saveUser(User newUser);
	
	User updateUser(User user);

	User getUserByUsernameAndPassword(String username, String password);
	
	void deleteUser(Integer userId);

}
