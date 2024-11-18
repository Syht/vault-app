package com.syht.vaultapp.service;

import com.syht.vaultapp.api.model.MangaDTO;
import com.syht.vaultapp.domain.Manga;
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
public class MangaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    /**
     * Constructor for dependencies injection
     *
     * @param pMediaRepository media repository
     * @param pMediaMapper media mapper
     */
    public MangaService(final MediaRepository pMediaRepository, final MediaMapper pMediaMapper) {
        mediaRepository = pMediaRepository;
        mediaMapper = pMediaMapper;
    }

    public MangaDTO createManga(final MangaDTO mangaDTO) {
        return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(mangaDTO)));
    }

    public boolean deleteManga(final Integer id) {
        try {
            this.mediaRepository.deleteById(id.longValue());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<MangaDTO> getAllManga(final Integer page, final Integer size) {
        Page<Manga> mangaPage = this.mediaRepository.findAllManga(PageRequest.of(page, size));
        return this.mediaMapper.mapList(mangaPage.getContent(), this.mediaMapper::toDto);
    }

    public MangaDTO getMangaById(final Integer id) {
        final Media media = this.mediaRepository.findById(id.longValue()).orElse(null);
        if (media instanceof Manga) {
            return this.mediaMapper.toDto((Manga) media);
        }
        return null;
    }

    public MangaDTO updateManga(final Integer id, final MangaDTO mangaDTO) {
        if (this.mediaRepository.existsById(id.longValue())) {
            mangaDTO.setId(id);
            return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(mangaDTO)));
        }
        return null;
    }
}
