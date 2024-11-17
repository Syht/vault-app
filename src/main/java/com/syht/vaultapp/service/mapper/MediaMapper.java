package com.syht.vaultapp.service.mapper;

import com.syht.vaultapp.api.model.AnimeDTO;
import com.syht.vaultapp.api.model.BookDTO;
import com.syht.vaultapp.api.model.ComicsDTO;
import com.syht.vaultapp.api.model.FilmDTO;
import com.syht.vaultapp.api.model.MangaDTO;
import com.syht.vaultapp.api.model.MediaGenre;
import com.syht.vaultapp.api.model.SeriesDTO;
import com.syht.vaultapp.domain.Anime;
import com.syht.vaultapp.domain.Book;
import com.syht.vaultapp.domain.Comics;
import com.syht.vaultapp.domain.Film;
import com.syht.vaultapp.domain.Genre;
import com.syht.vaultapp.domain.Manga;
import com.syht.vaultapp.domain.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    // Anime Mapping
    AnimeDTO toDto(Anime anime);
    List<AnimeDTO> toDtos(List<Anime> anime);
    @Mapping(target = "creationDate", ignore = true)
    Anime toEntity(AnimeDTO animeDTO);
    List<Anime> toEntities(List<AnimeDTO> animeDTO);

    // Series Mapping
    SeriesDTO toDto(Series series);
    List<SeriesDTO> toDtos(List<Series> series);
    @Mapping(target = "creationDate", ignore = true)
    Series toEntity(SeriesDTO seriesDTO);
    List<Series> toEntities(List<SeriesDTO> seriesDTO);

    // Book Mapping
    BookDTO toDto(Book book);
    List<BookDTO> toDtos(List<Book> books);
    @Mapping(target = "creationDate", ignore = true)
    Book toEntity(BookDTO bookDTO);
    List<Book> toEntities(List<BookDTO> bookDTO);

    // Comics Mapping
    ComicsDTO toDto(Comics comics);
    List<ComicsDTO> toDtos(List<Comics> comics);
    @Mapping(target = "creationDate", ignore = true)
    Comics toEntity(ComicsDTO comicsDTO);
    List<Comics> toEntities(List<ComicsDTO> comicsDTO);

    // Manga Mapping
    MangaDTO toDto(Manga manga);
    List<MangaDTO> toDtos(List<Manga> mangas);
    @Mapping(target = "creationDate", ignore = true)
    Manga toEntity(MangaDTO mangaDTO);
    List<Manga> toEntities(List<MangaDTO> mangaDTO);

    // Film Mapping
    FilmDTO toDto(Film film);
    List<FilmDTO> toDtos(List<Film> films);
    @Mapping(target = "creationDate", ignore = true)
    Film toEntity(FilmDTO filmDTO);
    List<Film> toEntities(List<FilmDTO> filmDTO);

    // Genre Mapping
    default Genre toGenre(MediaGenre mediaGenre) {
        return Genre.builder().name(mediaGenre).build();
    }

    default MediaGenre toMediaGenre(Genre genre) {
        if (genre == null || genre.getName() == null) {
            return null;
        }
        return genre.getName();
    }
}
