package com.syht.vaultapp.service;

import com.syht.vaultapp.api.model.FilmDTO;
import com.syht.vaultapp.domain.Film;
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
public class FilmService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    /**
     * Constructor for dependencies injection
     *
     * @param pMediaRepository media repository
     * @param pMediaMapper media mapper
     */
    public FilmService(final MediaRepository pMediaRepository, final MediaMapper pMediaMapper) {
        mediaRepository = pMediaRepository;
        mediaMapper = pMediaMapper;
    }

    public FilmDTO createFilm(final FilmDTO filmDTO) {
        return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(filmDTO)));
    }

    public boolean deleteFilm(final Integer id) {
        try {
            this.mediaRepository.deleteById(id.longValue());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<FilmDTO> getAllFilm(final Integer page, final Integer size) {
        Page<Film> filmPage = this.mediaRepository.findAllFilms(PageRequest.of(page, size));
        return this.mediaMapper.mapList(filmPage.getContent(), this.mediaMapper::toDto);
    }

    public FilmDTO getFilmById(final Integer id) {
        final Media media = this.mediaRepository.findById(id.longValue()).orElse(null);
        if (media instanceof Film) {
            return this.mediaMapper.toDto((Film) media);
        }
        return null;
    }

    public FilmDTO updateFilm(final Integer id, final FilmDTO filmDTO) {
        if (this.mediaRepository.existsById(id.longValue())) {
            filmDTO.setId(id);
            return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(filmDTO)));
        }
        return null;
    }
}
