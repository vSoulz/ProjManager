package polsl.pl.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import polsl.pl.pm.model.Project;

import java.util.List;

@Repository("projectRepository")
@CrossOrigin(origins = "http://localhost:4200")
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	List<Project> findByUserId(int userId);

}
