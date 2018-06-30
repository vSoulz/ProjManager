package polsl.pl.pm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.model.User;

import java.util.List;

@Repository("taskRepository")
@CrossOrigin(origins = "http://localhost:4200")
public interface TaskRepository extends JpaRepository<Task, String>{
	List<Task> findByUser(User user);
	List<Task> findByStatus(String status);
	List<Task> findByUserAndStatus(User user, String status);

}
