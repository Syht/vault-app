package com.syht.vaultapp.web.rest;

import com.syht.vaultapp.api.controller.MangaApi;
import com.syht.vaultapp.api.model.MangaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MangaResource implements MangaApi {

    /**
     * POST /manga : Create a new manga
     * Adds a new manga to the database.
     *
     * @param mangaDTO Manga object to be created (required)
     * @return Manga created successfully (status code 201)
     * or Invalid manga data (status code 400)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<MangaDTO> createManga(final MangaDTO mangaDTO) {
        return null;
    }

    /**
     * DELETE /manga/{id} : Delete a manga
     * Deletes a manga by its unique identifier.
     *
     * @param id The ID of the manga to delete (required)
     * @return Manga deleted successfully (status code 204)
     * or Manga not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteManga(final Integer id) {
        return null;
    }

    /**
     * GET /manga : Get all mangas
     * Retrieve a list of all mangas in the database, with optional pagination.
     *
     * @param page Page number for pagination (optional, default to 0)
     * @param size Page size for pagination (optional, default to 10)
     * @return List of mangas (status code 200)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<List<MangaDTO>> getAllManga(final Integer page, final Integer size) {
        return null;
    }

    /**
     * GET /manga/{id} : Get a specific manga by ID
     * Retrieve a specific manga by its unique identifier.
     *
     * @param id The ID of the manga to retrieve (required)
     * @return The manga object (status code 200)
     * or Manga not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<MangaDTO> getMangaById(final Integer id) {
        return null;
    }

    /**
     * PUT /manga/{id} : Update a manga
     * Update the details of an existing manga.
     *
     * @param id       The ID of the manga to update (required)
     * @param mangaDTO Updated manga object (required)
     * @return Manga updated successfully (status code 200)
     * or Invalid manga data (status code 400)
     * or Manga not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> updateManga(final Integer id, final MangaDTO mangaDTO) {
        return null;
    }
}
