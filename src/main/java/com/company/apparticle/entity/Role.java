package com.company.apparticle.entity;

import com.company.apparticle.entity.abs.AbsOwner;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends AbsOwner {
    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    boolean enabled;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    Attachment photo;

    List<Role> roles;


}
