package authentication.domain;


import jakarta.persistence.*;

@Entity
@Table(
        name="users",
        uniqueConstraints = @UniqueConstraint(name = "unique_users_username", columnNames = "user_name")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String username;

    @Column(name="user_password")
    private String password;

    @Column(name="user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
