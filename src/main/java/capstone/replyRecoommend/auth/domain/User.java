package capstone.replyRecoommend.auth.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private Long money;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String name){
        this.email = email;
        this.name = name;
        this.money = 0L;
        this.role = Role.USER;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        private final String value;
    }

    public boolean validateByEmailAndId(Long id, String email){
        return this.id.equals(id) && this.email.equals(email);
    }
}
