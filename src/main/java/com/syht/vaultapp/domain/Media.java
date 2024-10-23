package com.syht.vaultapp.domain;

import com.syht.vaultapp.api.model.ProgressState;
import com.syht.vaultapp.api.model.ReleaseState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    private LocalDate releaseDate;

    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "media_genre", joinColumns = @JoinColumn(name = "media_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    private BigDecimal rating;

    @Lob
    private byte[] coverImage;

    @Lob
    private byte[] thumbnail;

    @CreationTimestamp
    private Instant creationDate;

    @Enumerated(EnumType.STRING)
    private ReleaseState releaseState;

    @Enumerated(EnumType.STRING)
    private ProgressState progressState;
}
