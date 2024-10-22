package com.syht.vaultapp.domain;

import com.syht.vaultapp.api.model.Demographic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "manga")
public class Manga extends Media {

    private String mangaka;

    private String illustrator;

    private Integer chaptersCount;

    private Integer volumeCount;

    @Enumerated(EnumType.STRING)
    private Demographic demographic;
}
