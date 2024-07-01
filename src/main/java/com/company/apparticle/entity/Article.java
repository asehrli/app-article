package com.company.apparticle.entity;

import com.company.apparticle.entity.abs.AbsId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"first_id", "second_id"}),
        @UniqueConstraint(columnNames = {"second_id", "first_id"})
})
public class Follow extends AbsId {
    @OneToOne(optional = false)
    User first;

    @OneToOne(optional = false)
    User second;

    boolean firstFollowed;

    boolean secondFollowed;

    @CreationTimestamp
    LocalDateTime firstFollowedAt;

    LocalDateTime secondFollowedAt;
}
