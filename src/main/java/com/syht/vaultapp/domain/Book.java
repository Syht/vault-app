package com.syht.vaultapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
@PrimaryKeyJoinColumn(name = "media_id")
public class Book extends Media {

    private String author;

    private String edition;

    private Integer pageCount;
}
