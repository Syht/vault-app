package com.syht.vaultapp.web.rest;

import com.syht.vaultapp.api.controller.SeriesApi;
import com.syht.vaultapp.api.model.SeriesDTO;
import com.syht.vaultapp.service.SeriesService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class SeriesResource implements SeriesApi {

    private final SeriesService seriesService;

    public SeriesResource(final SeriesService pSeriesService) {
        seriesService = pSeriesService;
    }

    /**
     * POST /series : Create a new series
     * Adds a new series to the database.
     *
     * @param pSeriesDTO Series object to be created (required)
     * @return Series created successfully (status code 201)
     * or Invalid series data (status code 400)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<SeriesDTO> createSeries(final SeriesDTO pSeriesDTO) {
        final SeriesDTO seriesDTO = seriesService.createSeries(pSeriesDTO);
        return ResponseEntity.created(URI.create("/series/" + seriesDTO.getId())).body(seriesDTO);
    }

    /**
     * DELETE /series/{id} : Delete an series
     * Deletes an series by its unique identifier.
     *
     * @param id The ID of the series to delete (required)
     * @return Series deleted successfully (status code 204)
     * or Series not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteSeries(final Integer id) {
        if (seriesService.deleteSeries(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * GET /series : Get all series
     * Retrieve a list of all series in the database, with optional pagination.
     *
     * @param page Page number for pagination (optional, default to 0)
     * @param size Page size for pagination (optional, default to 10)
     * @return List of series (status code 200)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<List<SeriesDTO>> getAllSeries(final Integer page, final Integer size) {
        return ResponseEntity.ok(this.seriesService.getAllSeries(page, size));
    }

    /**
     * GET /series/{id} : Get a specific series by ID
     * Retrieve a specific series by its unique identifier.
     *
     * @param id The ID of the series to retrieve (required)
     * @return The series object (status code 200)
     * or Series not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<SeriesDTO> getSeriesById(final Integer id) {
        final SeriesDTO seriesDTO = this.seriesService.getSeriesById(id);
        if (seriesDTO != null) {
            return ResponseEntity.ok(seriesDTO);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PUT /series/{id} : Update an series
     * Update the details of an existing series.
     *
     * @param id       The ID of the series to update (required)
     * @param pSeriesDTO Updated series object (required)
     * @return Series updated successfully (status code 200)
     * or Invalid series data (status code 400)
     * or Series not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<SeriesDTO> updateSeries(final Integer id, final SeriesDTO pSeriesDTO) {
        final SeriesDTO seriesDTO = this.seriesService.updateSeries(id, pSeriesDTO);
        if (seriesDTO != null) {
            return ResponseEntity.ok(seriesDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
