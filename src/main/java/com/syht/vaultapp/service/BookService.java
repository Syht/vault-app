package com.syht.vaultapp.service;

import com.syht.vaultapp.api.model.BookDTO;
import com.syht.vaultapp.domain.Book;
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
public class BookService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    /**
     * Constructor for dependencies injection
     *
     * @param pMediaRepository media repository
     * @param pMediaMapper media mapper
     */
    public BookService(final MediaRepository pMediaRepository, final MediaMapper pMediaMapper) {
        mediaRepository = pMediaRepository;
        mediaMapper = pMediaMapper;
    }

    public BookDTO createBook(final BookDTO bookDTO) {
        return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(bookDTO)));
    }

    public boolean deleteBook(final Integer id) {
        try {
            this.mediaRepository.deleteById(id.longValue());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<BookDTO> getAllBook(final Integer page, final Integer size) {
        Page<Book> bookPage = this.mediaRepository.findAllBooks(PageRequest.of(page, size));
        return this.mediaMapper.mapList(bookPage.getContent(), this.mediaMapper::toDto);
    }

    public BookDTO getBookById(final Integer id) {
        final Media media = this.mediaRepository.findById(id.longValue()).orElse(null);
        if (media instanceof Book) {
            return this.mediaMapper.toDto((Book) media);
        }
        return null;
    }

    public BookDTO updateBook(final Integer id, final BookDTO bookDTO) {
        if (this.mediaRepository.existsById(id.longValue())) {
            bookDTO.setId(id);
            return this.mediaMapper.toDto(this.mediaRepository.save(this.mediaMapper.toEntity(bookDTO)));
        }
        return null;
    }
}
