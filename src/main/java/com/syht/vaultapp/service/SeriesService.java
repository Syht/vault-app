package com.syht.vaultapp.service;

import com.syht.vaultapp.api.model.SeriesDTO;
import com.syht.vaultapp.domain.Media;
import com.syht.vaultapp.domain.Series;
import com.syht.vaultapp.repository.MediaRepository;
import com.syht.vaultapp.service.mapper.MediaMapper;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SeriesService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    /**
     * Constructor for dependencies injection
     *
     * @param pMediaRepository media repository
     * @param pMediaMapper media mapper
     */
    public SeriesService(final MediaRepository pMediaRepository, final MediaMapper pMediaMapper) {
        mediaRepository = pMediaRepository;
        mediaMapper = pMediaMapper;
    }

    public SeriesDTO createSeries(final SeriesDTO seriesDTO) {
        return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(seriesDTO)));
    }

    public boolean deleteSeries(final Integer id) {
        try {
            this.mediaRepository.deleteById(id.longValue());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<SeriesDTO> getAllSeries(final Integer page, final Integer size) {
        Page<Series> seriesPage = this.mediaRepository.findAllSeries(PageRequest.of(page, size));
        return this.mediaMapper.mapList(seriesPage.getContent(), this.mediaMapper::toDto);
    }

    public SeriesDTO getSeriesById(final Integer id) {
        final Media media = this.mediaRepository.findById(id.longValue()).orElse(null);
        if (media instanceof Series) {
            return this.mediaMapper.toDto((Series) media);
        }
        return null;
    }

    public SeriesDTO updateSeries(final Integer id, final SeriesDTO seriesDTO) {
        if (this.mediaRepository.existsById(id.longValue())) {
            seriesDTO.setId(id);
            return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(seriesDTO)));
        }
        return null;
    }
}
