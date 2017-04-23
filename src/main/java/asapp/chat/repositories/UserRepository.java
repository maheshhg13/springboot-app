package asapp.chat.repositories;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import asapp.chat.models.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long>{
	public List<User> findByUserName(String userName);
}
