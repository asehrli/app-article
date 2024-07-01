package com.company.apparticle.entity;

import com.company.apparticle.entity.abs.AbsOwner;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
public class ArticleLike extends AbsOwner {
    @Column(nullable = false)
    @Lob
    String text;

    @ManyToOne(optional = false)
    Article article;
}