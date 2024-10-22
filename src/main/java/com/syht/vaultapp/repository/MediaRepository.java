package com.syht.vaultapp.repository;

import com.syht.vaultapp.api.model.AdaptedFrom;
import com.syht.vaultapp.api.model.Demographic;
import com.syht.vaultapp.api.model.EpisodeFormat;
import com.syht.vaultapp.api.model.ProgressState;
import com.syht.vaultapp.api.model.ReleaseState;
import com.syht.vaultapp.domain.Anime;
import com.syht.vaultapp.domain.Book;
import com.syht.vaultapp.domain.Comics;
import com.syht.vaultapp.domain.Film;
import com.syht.vaultapp.domain.Manga;
import com.syht.vaultapp.domain.Media;
import com.syht.vaultapp.domain.Series;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Media} entity.
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    // ---------------------------------------------
    // Common methods for Media entity and its children
    // ---------------------------------------------

    List<Media> findByTitleContainingIgnoreCase(String title);

    List<Media> findByReleaseState(ReleaseState releaseState);

    List<Media> findByProgressState(ProgressState progressState);

    List<Media> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    // ---------------------------------------------
    // Manga specific queries
    // ---------------------------------------------

    @Query("SELECT m FROM Manga m WHERE m.id = ?1")
    Optional<Manga> findMangaById(Long id);

    @Query("SELECT m FROM Manga m")
    List<Manga> findAllManga();

    List<Manga> findByMangakaContainingIgnoreCase(String mangaka);

    List<Manga> findByIllustratorContainingIgnoreCase(String illustrator);

    List<Manga> findByChaptersCountGreaterThanEqual(Integer chaptersCount);

    List<Manga> findByDemographic(Demographic demographic);

    // ---------------------------------------------
    // Series specific queries
    // ---------------------------------------------

    @Query("SELECT s FROM Series s WHERE s.id = ?1")
    Optional<Manga> findSeriesById(Long id);

    @Query("SELECT s FROM Series s")
    List<Series> findAllSeries();

    List<Series> findByShowrunnerContainingIgnoreCase(String showrunner);

    List<Series> findByNetworkContainingIgnoreCase(String network);

    List<Series> findBySeasonsCountGreaterThanEqual(Integer seasonsCount);

    List<Series> findByEpisodeFormat(EpisodeFormat episodeFormat);

    // ---------------------------------------------
    // Film specific queries
    // ---------------------------------------------

    @Query("SELECT f FROM Film f WHERE f.id = ?1")
    Optional<Manga> findFilmById(Long id);

    @Query("SELECT f FROM Film f")
    List<Film> findAllFilms();

    List<Film> findByDirectorContainingIgnoreCase(String director);

    List<Film> findBySagaContainingIgnoreCase(String saga);

    List<Film> findByDurationGreaterThanEqual(Integer duration);

    List<Film> findByBoxOfficeGreaterThanEqual(Float boxOffice);

    List<Film> findByBudgetGreaterThanEqual(Float budget);

    // ---------------------------------------------
    // Comics specific queries
    // ---------------------------------------------

    @Query("SELECT c FROM Comics c WHERE c.id = ?1")
    Optional<Comics> findComicsById(Long id);

    @Query("SELECT c FROM Comics c")
    List<Comics> findAllComics();

    List<Comics> findByScenaristContainingIgnoreCase(String scenarist);

    List<Comics> findByIllustratorContainingIgnoreCaseAndIdNotNull(String illustrator);

    List<Comics> findByVolumeCountGreaterThanEqual(Integer volumeCount);

    List<Comics> findByPublisherContainingIgnoreCase(String publisher);

    // ---------------------------------------------
    // Book specific queries
    // ---------------------------------------------

    @Query("SELECT b FROM Book b WHERE b.id = ?1")
    Optional<Book> findBookById(Long id);

    @Query("SELECT b FROM Book b")
    List<Book> findAllBooks();

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByEditionContainingIgnoreCase(String edition);

    List<Book> findByPageCountGreaterThanEqual(Integer pageCount);

    // ---------------------------------------------
    // Anime specific queries
    // ---------------------------------------------

    @Query("SELECT a FROM Anime a WHERE a.id = ?1")
    Optional<Anime> findAnimeById(Long id);

    @Query("SELECT a FROM Anime a")
    List<Anime> findAllAnime();

    List<Anime> findByStudioContainingIgnoreCase(String studio);

    List<Anime> findByEpisodesCountGreaterThanEqual(Integer episodesCount);

    List<Anime> findByAdaptedFrom(AdaptedFrom adaptedFrom);
}
