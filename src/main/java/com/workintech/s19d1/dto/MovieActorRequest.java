package com.workintech.s19d1.dto;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

public class MovieActorRequest {
    private Movie movie;
    private Actor actor;

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public Actor getActor() { return actor; }
    public void setActor(Actor actor) { this.actor = actor; }
}