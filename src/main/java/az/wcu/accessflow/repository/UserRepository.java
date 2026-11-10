package az.wcu.accessflow.repository;

import az.wcu.accessflow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);   // SQL Spring пишет сам по имени метода
    boolean existsByUsername(String username);
}