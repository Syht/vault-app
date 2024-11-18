package com.syht.vaultapp.web.rest;

import com.syht.vaultapp.api.controller.MangaApi;
import com.syht.vaultapp.api.model.MangaDTO;
import com.syht.vaultapp.service.MangaService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class MangaResource implements MangaApi {

    private final MangaService mangaService;

    public MangaResource(final MangaService pMangaService) {
        mangaService = pMangaService;
    }

    /**
     * POST /manga : Create a new manga
     * Adds a new manga to the database.
     *
     * @param pMangaDTO Manga object to be created (required)
     * @return Manga created successfully (status code 201)
     * or Invalid manga data (status code 400)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<MangaDTO> createManga(final MangaDTO pMangaDTO) {
        final MangaDTO mangaDTO = mangaService.createManga(pMangaDTO);
        return ResponseEntity.created(URI.create("/manga/" + mangaDTO.getId())).body(mangaDTO);
    }

    /**
     * DELETE /manga/{id} : Delete an manga
     * Deletes an manga by its unique identifier.
     *
     * @param id The ID of the manga to delete (required)
     * @return Manga deleted successfully (status code 204)
     * or Manga not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteManga(final Integer id) {
        if (mangaService.deleteManga(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * GET /manga : Get all manga
     * Retrieve a list of all manga in the database, with optional pagination.
     *
     * @param page Page number for pagination (optional, default to 0)
     * @param size Page size for pagination (optional, default to 10)
     * @return List of manga (status code 200)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<List<MangaDTO>> getAllManga(final Integer page, final Integer size) {
        return ResponseEntity.ok(this.mangaService.getAllManga(page, size));
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
        final MangaDTO mangaDTO = this.mangaService.getMangaById(id);
        if (mangaDTO != null) {
            return ResponseEntity.ok(mangaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PUT /manga/{id} : Update an manga
     * Update the details of an existing manga.
     *
     * @param id       The ID of the manga to update (required)
     * @param pMangaDTO Updated manga object (required)
     * @return Manga updated successfully (status code 200)
     * or Invalid manga data (status code 400)
     * or Manga not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<MangaDTO> updateManga(final Integer id, final MangaDTO pMangaDTO) {
        final MangaDTO mangaDTO = this.mangaService.updateManga(id, pMangaDTO);
        if (mangaDTO != null) {
            return ResponseEntity.ok(mangaDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
