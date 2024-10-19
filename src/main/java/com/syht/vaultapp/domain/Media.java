package com.syht.vaultapp.domain;

import com.syht.vaultapp.domain.enums.MediaGenre;
import com.syht.vaultapp.domain.enums.ProgressState;
import com.syht.vaultapp.domain.enums.ReleaseState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "media")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Media implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    private String title;

    private String description;

    private Date releaseDate;

    private Date endDate;

    @ElementCollection
    @CollectionTable(name = "media_genre", joinColumns = @JoinColumn(name = "media_id"))
    @Column(name = "genre")
    private Set<MediaGenre> genre;

    private BigDecimal rating;

    @Lob
    private byte[] coverImage;

    @Lob
    private byte[] thumbnail;

    private Instant creationDate;

    @Enumerated(EnumType.STRING)
    private ReleaseState releaseState;

    @Enumerated(EnumType.STRING)
    private ProgressState progressState;
}
