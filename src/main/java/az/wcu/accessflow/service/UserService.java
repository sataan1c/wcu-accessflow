package az.wcu.accessflow.service;

import az.wcu.accessflow.domain.Role;
import az.wcu.accessflow.domain.User;
import az.wcu.accessflow.repository.UserRepository;
import az.wcu.accessflow.web.RegisterForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public UserService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    // Вызывается Spring Security при входе
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRole().name())
                .build();
    }

    @Transactional
    public void register(RegisterForm form) {
        if (users.existsByUsername(form.getUsername())) {
            throw new IllegalArgumentException("This username is already taken");
        }

        // При регистрации всегда роль USER
        users.save(new User(
                form.getUsername(),
                encoder.encode(form.getPassword()),
                form.getFullName(),
                Role.USER));
    }
}