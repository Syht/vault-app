package com.syht.vaultapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comics")
public class Comics extends Media {

    private String scenarist;

    private Integer volumeCount;

    private String illustrator;

    private String colorist;

    private String publisher;
}
