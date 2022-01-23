package com.example.movie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {


    @Autowired
    MovieRepository repository;

    ArrayList<Movie> Movie = new ArrayList<>();

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getTitles() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }



    @GetMapping("/movies/random")
    public ResponseEntity<Movie> getRandomMovie() {
        List<Movie> movies = repository.findAll();

        int randomNum = (int) Math.floor(Math.random() * movies.size());

        return  ResponseEntity.status(HttpStatus.OK).body(movies.get(randomNum));
    }


    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findMovieByid(Integer.parseInt(id)));
    }


    @PostMapping("/movie")
    public ResponseEntity<String> createGreeting(@RequestBody Movie movie) {
        repository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added " + movie.getTitle());

    }

    @DeleteMapping("/movie/{id}")
    @Transactional
    public ResponseEntity<String> deleteMovie(@PathVariable String id) {
        repository.deleteMovieByid(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.OK).body("Movie with ID " + id + " has been deleted");

    }
}
