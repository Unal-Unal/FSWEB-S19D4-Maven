package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie createMovie(Movie movie);
    Optional<Movie> getMovieById(Long id);
    List<Movie> getAllMovies();
    Movie updateMovie(Long id, Movie movie);
    void deleteMovie(Long id);
    Movie createMovieWithActor(Movie movie, Actor actor);
}
