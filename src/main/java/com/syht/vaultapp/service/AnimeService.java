package com.syht.vaultapp.service;

import com.syht.vaultapp.api.model.AnimeDTO;
import com.syht.vaultapp.domain.Anime;
import com.syht.vaultapp.domain.Media;
import com.syht.vaultapp.repository.MediaRepository;
import com.syht.vaultapp.service.mapper.MediaMapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AnimeService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    /**
     * Constructor for dependencies injection
     *
     * @param pMediaRepository media repository
     * @param pMediaMapper media mapper
     */
    public AnimeService(final MediaRepository pMediaRepository, final MediaMapper pMediaMapper) {
        mediaRepository = pMediaRepository;
        mediaMapper = pMediaMapper;
    }

    public AnimeDTO createAnime(final AnimeDTO animeDTO) {
        return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(animeDTO)));
    }

    public boolean deleteAnime(final Integer id) {
        try {
            this.mediaRepository.deleteById(id.longValue());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<AnimeDTO> getAllAnime(final Integer page, final Integer size) {
        return this.mediaMapper.toDtos(this.mediaRepository.findAllAnime());
    }

    public AnimeDTO getAnimeById(final Integer id) {
        final Media media = this.mediaRepository.findById(id.longValue()).orElse(null);
        if (media instanceof Anime) {
            return this.mediaMapper.toDto((Anime) media);
        }
        return null;
    }

    public boolean updateAnime(final Integer id, final AnimeDTO animeDTO) {
        if (this.mediaRepository.existsById(id.longValue())) {
            animeDTO.setId(id);
            this.mediaRepository.save(this.mediaMapper.toEntity(animeDTO));
            return true;
        }
        return false;
    }
}
