package com.syht.vaultapp.web.rest;

import com.syht.vaultapp.api.controller.ComicsApi;
import com.syht.vaultapp.api.model.ComicsDTO;
import com.syht.vaultapp.service.ComicsService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class ComicsResource implements ComicsApi {

    private final ComicsService comicsService;

    public ComicsResource(final ComicsService pComicsService) {
        comicsService = pComicsService;
    }

    /**
     * POST /comics : Create a new comics
     * Adds a new comics to the database.
     *
     * @param pComicsDTO Comics object to be created (required)
     * @return Comics created successfully (status code 201)
     * or Invalid comics data (status code 400)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<ComicsDTO> createComics(final ComicsDTO pComicsDTO) {
        final ComicsDTO comicsDTO = comicsService.createComics(pComicsDTO);
        return ResponseEntity.created(URI.create("/comics/" + comicsDTO.getId())).body(comicsDTO);
    }

    /**
     * DELETE /comics/{id} : Delete an comics
     * Deletes an comics by its unique identifier.
     *
     * @param id The ID of the comics to delete (required)
     * @return Comics deleted successfully (status code 204)
     * or Comics not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteComics(final Integer id) {
        if (comicsService.deleteComics(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * GET /comics : Get all comics
     * Retrieve a list of all comics in the database, with optional pagination.
     *
     * @param page Page number for pagination (optional, default to 0)
     * @param size Page size for pagination (optional, default to 10)
     * @return List of comics (status code 200)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<List<ComicsDTO>> getAllComics(final Integer page, final Integer size) {
        return ResponseEntity.ok(this.comicsService.getAllComics(page, size));
    }

    /**
     * GET /comics/{id} : Get a specific comics by ID
     * Retrieve a specific comics by its unique identifier.
     *
     * @param id The ID of the comics to retrieve (required)
     * @return The comics object (status code 200)
     * or Comics not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<ComicsDTO> getComicsById(final Integer id) {
        final ComicsDTO comicsDTO = this.comicsService.getComicsById(id);
        if (comicsDTO != null) {
            return ResponseEntity.ok(comicsDTO);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PUT /comics/{id} : Update an comics
     * Update the details of an existing comics.
     *
     * @param id       The ID of the comics to update (required)
     * @param pComicsDTO Updated comics object (required)
     * @return Comics updated successfully (status code 200)
     * or Invalid comics data (status code 400)
     * or Comics not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<ComicsDTO> updateComics(final Integer id, final ComicsDTO pComicsDTO) {
        final ComicsDTO comicsDTO = this.comicsService.updateComics(id, pComicsDTO);
        if (comicsDTO != null) {
            return ResponseEntity.ok(comicsDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
