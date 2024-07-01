package com.company.apparticle.entity;

import com.company.apparticle.entity.abs.AbsOwner;
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
public class Comment extends AbsOwner {
    @Column(nullable = false)
    @Lob
    String text;

    @ManyToOne(optional = false)
    Article article;
}