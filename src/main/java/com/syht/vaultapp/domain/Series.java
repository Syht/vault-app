package com.syht.vaultapp.domain;

import com.syht.vaultapp.domain.enums.EpisodeFormat;
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
@Table(name = "series")
public class Series extends Media {

    private Integer seasonsCount;

    private Integer episodesCount;

    private String showrunner;

    private String network;

    @Enumerated(EnumType.STRING)
    private EpisodeFormat episodeFormat;
}
