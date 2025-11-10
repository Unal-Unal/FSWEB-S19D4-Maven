package com.workintech.s19d1.validation;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.entity.Gender;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class HollywoodValidation {

    public static void validateMovie(Movie movie) {
        if (movie.getName() == null || movie.getName().isBlank()) {
            throw new ApiException("Movie name cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (movie.getRating() < 0 || movie.getRating() > 10) {
            throw new ApiException("Movie rating must be between 0 and 10", HttpStatus.BAD_REQUEST);
        }
        if (movie.getReleaseDate() != null && movie.getReleaseDate().isAfter(LocalDate.now())) {
            throw new ApiException("Release date cannot be in the future", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateActor(Actor actor) {
        if (actor.getFirstName() == null || actor.getFirstName().isBlank()) {
            throw new ApiException("Actor firstName cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (actor.getLastName() == null || actor.getLastName().isBlank()) {
            throw new ApiException("Actor lastName cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (actor.getBirthDate() != null && actor.getBirthDate().isAfter(LocalDate.now())) {
            throw new ApiException("Actor birthDate cannot be in the future", HttpStatus.BAD_REQUEST);
        }
        if (actor.getGender() == null) {
            throw new ApiException("Actor gender must be specified", HttpStatus.BAD_REQUEST);
        }
    }
}