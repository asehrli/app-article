package com.company.apparticle.payload;

import com.company.apparticle.entity.Comment;
import com.company.apparticle.entity.Section;
import com.company.apparticle.entity.User;
import com.company.apparticle.entity.abs.AbsOwner;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ArticleDto extends AbsOwner {
    @OneToOne(optional = false)
    User user;

    @Column(nullable = false)
    String title;

    int views;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    List<Section> sections;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    List<Comment> comments;
}
