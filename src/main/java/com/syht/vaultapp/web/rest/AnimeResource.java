package com.syht.vaultapp.web.rest;

import com.syht.vaultapp.api.controller.AnimeApi;
import com.syht.vaultapp.api.model.AnimeDTO;
import com.syht.vaultapp.service.AnimeService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AnimeResource implements AnimeApi {

    private final AnimeService animeService;

    public AnimeResource(final AnimeService pAnimeService) {
        animeService = pAnimeService;
    }

    /**
     * POST /anime : Create a new anime
     * Adds a new anime to the database.
     *
     * @param animeDTO Anime object to be created (required)
     * @return Anime created successfully (status code 201)
     * or Invalid anime data (status code 400)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<AnimeDTO> createAnime(final AnimeDTO animeDTO) {
        return new ResponseEntity<>(animeService.createAnime(animeDTO), null, 200);
    }

    /**
     * DELETE /anime/{id} : Delete an anime
     * Deletes an anime by its unique identifier.
     *
     * @param id The ID of the anime to delete (required)
     * @return Anime deleted successfully (status code 204)
     * or Anime not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteAnime(final Integer id) {
        return null;
    }

    /**
     * GET /anime : Get all anime
     * Retrieve a list of all anime in the database, with optional pagination.
     *
     * @param page Page number for pagination (optional, default to 0)
     * @param size Page size for pagination (optional, default to 10)
     * @return List of anime (status code 200)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<List<AnimeDTO>> getAllAnime(final Integer page, final Integer size) {
        return null;
    }

    /**
     * GET /anime/{id} : Get a specific anime by ID
     * Retrieve a specific anime by its unique identifier.
     *
     * @param id The ID of the anime to retrieve (required)
     * @return The anime object (status code 200)
     * or Anime not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<AnimeDTO> getAnimeById(final Integer id) {
        return null;
    }

    /**
     * PUT /anime/{id} : Update an anime
     * Update the details of an existing anime.
     *
     * @param id       The ID of the anime to update (required)
     * @param animeDTO Updated anime object (required)
     * @return Anime updated successfully (status code 200)
     * or Invalid anime data (status code 400)
     * or Anime not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> updateAnime(final Integer id, final AnimeDTO animeDTO) {
        return null;
    }
}
