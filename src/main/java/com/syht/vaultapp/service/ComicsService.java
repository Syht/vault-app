package com.syht.vaultapp.service;

import com.syht.vaultapp.api.model.ComicsDTO;
import com.syht.vaultapp.domain.Comics;
import com.syht.vaultapp.domain.Media;
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
public class ComicsService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    /**
     * Constructor for dependencies injection
     *
     * @param pMediaRepository media repository
     * @param pMediaMapper media mapper
     */
    public ComicsService(final MediaRepository pMediaRepository, final MediaMapper pMediaMapper) {
        mediaRepository = pMediaRepository;
        mediaMapper = pMediaMapper;
    }

    public ComicsDTO createComics(final ComicsDTO comicsDTO) {
        return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(comicsDTO)));
    }

    public boolean deleteComics(final Integer id) {
        try {
            this.mediaRepository.deleteById(id.longValue());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<ComicsDTO> getAllComics(final Integer page, final Integer size) {
        Page<Comics> comicsPage = this.mediaRepository.findAllComics(PageRequest.of(page, size));
        return this.mediaMapper.mapList(comicsPage.getContent(), this.mediaMapper::toDto);
    }

    public ComicsDTO getComicsById(final Integer id) {
        final Media media = this.mediaRepository.findById(id.longValue()).orElse(null);
        if (media instanceof Comics) {
            return this.mediaMapper.toDto((Comics) media);
        }
        return null;
    }

    public ComicsDTO updateComics(final Integer id, final ComicsDTO comicsDTO) {
        if (this.mediaRepository.existsById(id.longValue())) {
            comicsDTO.setId(id);
            return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(comicsDTO)));
        }
        return null;
    }
}
