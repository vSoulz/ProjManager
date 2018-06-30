package polsl.pl.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import polsl.pl.pm.model.Role;

@Repository("roleRepository")
@CrossOrigin(origins = "http://localhost:4200")
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}
