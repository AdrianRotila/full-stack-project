package com.project.demo;

import com.project.demo.exceptions.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    // CREATE
    public void addMovie(Movie movie) {
        moviesRepository.save(movie);
    }

    // READ
    // ById
    public Movie getMovieById(long id) {
        Optional<Movie> movie = moviesRepository.findById(id);
        if (movie.isEmpty()) {
            throw new MovieNotFoundException();
        }
        return movie.get();
    }

    // All
    public List<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }

    // UPDATE
    public void updateMovie(Movie newMovie, long id) {
        if (!moviesRepository.existsById(id)) {
            throw new MovieNotFoundException();
        }
        newMovie.setId(id);
        moviesRepository.save(newMovie);
    }

    //Delete
    @Transactional
    public boolean deleteMovieById(long id) {
        if(!moviesRepository.existsById(id)) {
            throw new MovieNotFoundException();
        }
        moviesRepository.deleteMovieById(id);
        return true;
    }
}
