package pl.softwarelady.sbj.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.softwarelady.sbj.jpa.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long>{

}
