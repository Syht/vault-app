package com.syht.vaultapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syht.vaultapp.IntegrationTest;
import com.syht.vaultapp.api.model.Demographic;
import com.syht.vaultapp.api.model.MangaDTO;
import com.syht.vaultapp.api.model.MediaGenre;
import com.syht.vaultapp.api.model.ProgressState;
import com.syht.vaultapp.api.model.ReleaseState;
import com.syht.vaultapp.domain.Genre;
import com.syht.vaultapp.domain.Manga;
import com.syht.vaultapp.repository.MediaRepository;
import com.syht.vaultapp.security.AuthoritiesConstants;
import com.syht.vaultapp.service.MangaService;
import com.syht.vaultapp.service.mapper.MediaMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MangaResource} REST controller.
 */
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@IntegrationTest
class MangaResourceIT {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MangaService mangaService;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private MockMvc restUserMockMvc;

    private Manga manga;

    private Long numberOfMangas;

    @BeforeEach
    public void countMangas() {
        numberOfMangas = mediaRepository.countManga();
        manga = createEntity();
    }

    /**
     * Create a Manga.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which has a required relationship to the Manga entity.
     */
    public static Manga createEntity() {
        Manga persistManga = new Manga();
        persistManga.setTitle(RandomStringUtils.randomAlphanumeric(8));
        persistManga.setMangaka(RandomStringUtils.randomAlphanumeric(60));
        persistManga.setDemographic(Demographic.SEINEN);
        persistManga.setIllustrator(RandomStringUtils.randomAlphabetic(5));
        persistManga.setVolumeCount(10);
        persistManga.setChaptersCount(100);
        persistManga.setDescription(RandomStringUtils.randomAlphanumeric(1000));
        persistManga.setProgressState(ProgressState.IN_PROGRESS);
        persistManga.setGenres(Set.of(Genre.builder().name(MediaGenre.ACTION).build(), Genre.builder().name(MediaGenre.CYBERPUNK).build()));
        persistManga.setThumbnail(new byte[] { 1, 2, 3 });
        persistManga.setCoverImage(new byte[] { 1, 2, 3 });
        return persistManga;
    }

    @AfterEach
    public void cleanupAndCheck() {
        cacheManager
            .getCacheNames()
            .stream()
            .map(cacheName -> this.cacheManager.getCache(cacheName))
            .filter(Objects::nonNull)
            .forEach(Cache::clear);
        if (manga.getId() != null && mangaService.deleteManga(Math.toIntExact(manga.getId()))) {
            assertThat(mediaRepository.countManga()).isEqualTo(numberOfMangas);
        }
        numberOfMangas = null;
    }

    @Test
    @Transactional
    void createManga() throws Exception {
        // Create the Manga
        MangaDTO mangaDTO = MangaDTO.builder()
            .demographic(Demographic.SEINEN)
            .chaptersCount(400)
            .description("description")
            .mangaka("mangaka")
            .endDate(LocalDate.now())
            .coverImage(new byte[] { 1, 2, 3 })
            .thumbnail(new byte[] { 1, 2, 3 })
            .illustrator("illustrator")
            .genres(Collections.singleton(MediaGenre.FANTASY))
            .progressState(ProgressState.TO_CONSUME)
            .title("title")
            .releaseState(ReleaseState.RELEASED)
            .releaseDate(LocalDate.now())
            .build();

        var returnedMangaDTO = om.readValue(
            restUserMockMvc
                .perform(post("/manga").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mangaDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MangaDTO.class
        );

        // Validate the returned Manga
        assertThat(returnedMangaDTO.getMangaka()).isEqualTo(mangaDTO.getMangaka());
        assertThat(returnedMangaDTO.getProgressState()).isEqualTo(mangaDTO.getProgressState());
        assertThat(returnedMangaDTO.getReleaseState()).isEqualTo(mangaDTO.getReleaseState());
        assertThat(returnedMangaDTO.getId()).isNotNull();
    }

    @Test
    @Transactional
    void getAllMangas() throws Exception {
        // Get all the mangas
        restUserMockMvc
            .perform(get("/mangas"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Transactional
    void getManga() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(manga);

        // Get the manga
        restUserMockMvc
            .perform(get("/manga/{id}", manga.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(manga.getId()))
            .andExpect(jsonPath("$.mangaka").value(manga.getMangaka()));
    }

    @Test
    @Transactional
    void getNonExistingManga() throws Exception {
        restUserMockMvc.perform(get("/manga/99999")).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateManga() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(manga);
        int databaseSizeBeforeUpdate = mediaRepository.findAll().size();

        // Update the manga
        Manga updatedManga = mediaRepository.findMangaById(manga.getId()).orElseThrow();

        MangaDTO mangaDTO = mediaMapper.toDto(updatedManga);
        mangaDTO.setMangaka("nouveauMangaka");

        restUserMockMvc
            .perform(put("/manga/{id}", manga.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(manga.getId()))
            .andExpect(jsonPath("$.mangaka").value(mangaDTO.getMangaka()));
    }

    @Test
    @Transactional
    void deleteManga() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(manga);
        int databaseSizeBeforeDelete = mediaRepository.findAll().size();

        // Delete the manga
        restUserMockMvc
            .perform(MockMvcRequestBuilders.delete("/manga/{id}", manga.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        Assertions.assertThat(mediaRepository.count()).isEqualTo(databaseSizeBeforeDelete - 1);
    }
}
