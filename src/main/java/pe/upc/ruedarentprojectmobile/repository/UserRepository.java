package pe.upc.ruedarentprojectmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.ruedarentprojectmobile.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsUserByEmail(String email);

}
