package az.wcu.accessflow.config;

import az.wcu.accessflow.domain.Role;
import az.wcu.accessflow.domain.User;
import az.wcu.accessflow.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private static final String DEMO_PASSWORD = "demo123";

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public DataLoader(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (users.count() > 0) {
            return; // база уже заполнена
        }

        users.save(new User("admin", encoder.encode(DEMO_PASSWORD), "System Admin", Role.ADMIN));
        users.save(new User("reviewer1", encoder.encode(DEMO_PASSWORD), "Reviewer One", Role.REVIEWER));
        users.save(new User("user1", encoder.encode(DEMO_PASSWORD), "User One", Role.USER));
    }
}