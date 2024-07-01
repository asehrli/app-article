package com.company.apparticle.entity;

import com.company.apparticle.entity.abs.AbsTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title", "article_id"}))
public class Section extends AbsTime {
    @Column(nullable = false)
    String title;

    @Lob
    String body;

    @ManyToOne(optional = false)
    Article article;

    @OneToOne
    Attachment image;
}