package pl.softwarelady.sbj.services;

import java.util.List;

import pl.softwarelady.sbj.jpa.UserModel;

public interface UserService {
	
	List<UserModel> allUsers();

    UserModel getById(Long id);

    UserModel merge(UserModel user);

    void delete(Long id);

    //User saveOrUpdateProductForm(UserForm userForm);
}
