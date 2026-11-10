package az.wcu.accessflow.domain;

import jakarta.persistence.*;

@Entity                       // этот класс = таблица в базе
@Table(name = "users")        // "user" — зарезервированное слово в H2
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // id создаёт база
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;                          // только хеш, не пароль

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING)                          // в базе хранится "ADMIN", а не 2
    @Column(nullable = false, length = 20)
    private Role role;

    protected User() { }                                  // нужен JPA

    public User(String username, String passwordHash, String fullName, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }
    public Role getRole() { return role; }
}