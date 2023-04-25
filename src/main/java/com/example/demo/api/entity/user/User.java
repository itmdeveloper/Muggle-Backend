package com.example.demo.api.entity.user;

import com.example.demo.oauth.entity.ProviderType;
import com.example.demo.oauth.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true)
    private String userId;

    @Column(length = 100)
    private String username;

    @Column(length = 128)
    private String password;

    @Column(length = 512)
    private String email;

    @Column(length = 500)
    private String emailVerifiedYn;

    @Column(length = 512)
    private String profileImageUrl;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(length = 20)
    private RoleType roleType;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;


    public User(String userId,
                String username,
                String profileImageUrl,
                String email,
                String emailVerifiedYn,
                ProviderType providerType,
                RoleType roleType,
                LocalDateTime createAt,
                LocalDateTime modifiedAt) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "NO_IMAGE";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }


}
