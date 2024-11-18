package com.syht.vaultapp.web.rest;

import com.syht.vaultapp.api.controller.FilmApi;
import com.syht.vaultapp.api.model.FilmDTO;
import com.syht.vaultapp.service.FilmService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class FilmResource implements FilmApi {

    private final FilmService filmService;

    public FilmResource(final FilmService pFilmService) {
        filmService = pFilmService;
    }

    /**
     * POST /film : Create a new film
     * Adds a new film to the database.
     *
     * @param pFilmDTO Film object to be created (required)
     * @return Film created successfully (status code 201)
     * or Invalid film data (status code 400)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<FilmDTO> createFilm(final FilmDTO pFilmDTO) {
        final FilmDTO filmDTO = filmService.createFilm(pFilmDTO);
        return ResponseEntity.created(URI.create("/film/" + filmDTO.getId())).body(filmDTO);
    }

    /**
     * DELETE /film/{id} : Delete an film
     * Deletes an film by its unique identifier.
     *
     * @param id The ID of the film to delete (required)
     * @return Film deleted successfully (status code 204)
     * or Film not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteFilm(final Integer id) {
        if (filmService.deleteFilm(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * GET /film : Get all film
     * Retrieve a list of all film in the database, with optional pagination.
     *
     * @param page Page number for pagination (optional, default to 0)
     * @param size Page size for pagination (optional, default to 10)
     * @return List of film (status code 200)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<List<FilmDTO>> getAllFilms(final Integer page, final Integer size) {
        return ResponseEntity.ok(this.filmService.getAllFilm(page, size));
    }

    /**
     * GET /film/{id} : Get a specific film by ID
     * Retrieve a specific film by its unique identifier.
     *
     * @param id The ID of the film to retrieve (required)
     * @return The film object (status code 200)
     * or Film not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<FilmDTO> getFilmById(final Integer id) {
        final FilmDTO filmDTO = this.filmService.getFilmById(id);
        if (filmDTO != null) {
            return ResponseEntity.ok(filmDTO);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PUT /film/{id} : Update an film
     * Update the details of an existing film.
     *
     * @param id       The ID of the film to update (required)
     * @param pFilmDTO Updated film object (required)
     * @return Film updated successfully (status code 200)
     * or Invalid film data (status code 400)
     * or Film not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<FilmDTO> updateFilm(final Integer id, final FilmDTO pFilmDTO) {
        final FilmDTO filmDTO = this.filmService.updateFilm(id, pFilmDTO);
        if (filmDTO != null) {
            return ResponseEntity.ok(filmDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
