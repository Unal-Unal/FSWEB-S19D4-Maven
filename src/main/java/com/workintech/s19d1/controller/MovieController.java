package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // [GET] /workintech/movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // [GET] /workintech/movies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // [POST] /workintech/movies
    @PostMapping
    public ResponseEntity<Movie> createMovieWithActor(@RequestBody MovieActorRequest request) {
        Movie savedMovie = movieService.createMovieWithActor(request.getMovie(), request.getActor());
        return ResponseEntity.ok(savedMovie);
    }

    // [PUT] /workintech/movies/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updated = movieService.updateMovie(id, movie);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // [DELETE] /workintech/movies/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    // DTO sınıfı: Movie ve Actor birlikte POST request için
    public static class MovieActorRequest {
        private Movie movie;
        private Actor actor;

        public Movie getMovie() { return movie; }
        public void setMovie(Movie movie) { this.movie = movie; }

        public Actor getActor() { return actor; }
        public void setActor(Actor actor) { this.actor = actor; }
    }
}
