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
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    // Anime Mapping
    AnimeDTO toDto(Anime anime);

    @Mapping(target = "creationDate", ignore = true)
    Anime toEntity(AnimeDTO animeDTO);

    // Series Mapping
    SeriesDTO toDto(Series series);

    @Mapping(target = "creationDate", ignore = true)
    Series toEntity(SeriesDTO seriesDTO);

    // Book Mapping
    BookDTO toDto(Book book);

    @Mapping(target = "creationDate", ignore = true)
    Book toEntity(BookDTO bookDTO);

    // Comics Mapping
    ComicsDTO toDto(Comics comics);

    @Mapping(target = "creationDate", ignore = true)
    Comics toEntity(ComicsDTO comicsDTO);

    // Manga Mapping
    MangaDTO toDto(Manga manga);

    @Mapping(target = "creationDate", ignore = true)
    Manga toEntity(MangaDTO mangaDTO);

    // Film Mapping
    FilmDTO toDto(Film film);

    @Mapping(target = "creationDate", ignore = true)
    Film toEntity(FilmDTO filmDTO);

    // Generic list mapping
    default <T, U> List<U> mapList(List<T> objects, Function<T, U> mapper) {
        return objects.stream().map(mapper).collect(Collectors.toList());
    }

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
