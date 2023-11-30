package com.nashtech.kpi.entity;

import com.nashtech.kpi.model.Provider;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;

    private String googleId;

    @NonNull
    private String email;

    private Provider provider;


    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "ID") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "ID") })
    private Set<RoleEntity> authorities;
    private Boolean enabled = true;

}
