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

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    AnimeDTO toDto(Anime anime);

    @Mapping(target = "creationDate", ignore = true)
    Anime toEntity(AnimeDTO animeDTO);

    SeriesDTO toDto(Series series);

    @Mapping(target = "creationDate", ignore = true)
    Series toEntity(SeriesDTO seriesDTO);

    BookDTO toDto(Book book);

    @Mapping(target = "creationDate", ignore = true)
    Book toEntity(BookDTO bookDTO);

    ComicsDTO toDto(Comics comics);

    @Mapping(target = "creationDate", ignore = true)
    Comics toEntity(ComicsDTO comicsDTO);

    MangaDTO toDto(Manga manga);

    @Mapping(target = "creationDate", ignore = true)
    Manga toEntity(MangaDTO mangaDTO);

    FilmDTO toDto(Film film);

    @Mapping(target = "creationDate", ignore = true)
    Film toEntity(FilmDTO filmDTO);

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
