package com.workintech.s19d1.dto;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

public class ActorMovieRequest {
    private Actor actor;
    private Movie movie;

    public Actor getActor() { return actor; }
    public void setActor(Actor actor) { this.actor = actor; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
}