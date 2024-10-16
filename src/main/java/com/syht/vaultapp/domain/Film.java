package com.syht.vaultapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "film")
public class Film extends Media {

    private String director;

    private Integer duration;

    private BigDecimal boxOffice;

    private BigDecimal budget;

    private String saga;
}
