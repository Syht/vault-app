package com.syht.vaultapp.domain;

import com.syht.vaultapp.domain.enums.AdaptedFrom;
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
@Table(name = "anime")
public class Anime extends Media {

    private String studio;

    private Integer episodesCount;

    @Enumerated(EnumType.STRING)
    private AdaptedFrom adaptedFrom;
}
