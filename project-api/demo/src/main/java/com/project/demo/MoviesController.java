package com.project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3002")
public class MoviesController {

    @Autowired
    MoviesService moviesService;


    // CREATE
    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        moviesService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    // READ
    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long id){
        return ResponseEntity.ok(moviesService.getMovieById(id));
    }
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(moviesService.getAllMovies());
    }

    // UPDATE
    @PutMapping("movie/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie newMovie, @PathVariable long id) {
        newMovie.setId(id);
        moviesService.updateMovie(newMovie, id);
        return ResponseEntity.status(HttpStatus.OK).body(newMovie);
    }

    // DELETE
    @DeleteMapping("/movie/{id}")
    @Transactional
    public ResponseEntity<Long> deleteMovie(@PathVariable long id) {
       return moviesService.deleteMovieById(id) ?
               new ResponseEntity<>(id, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
