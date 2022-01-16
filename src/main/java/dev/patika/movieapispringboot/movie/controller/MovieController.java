package dev.patika.movieapispringboot.movie.controller;

import dev.patika.movieapispringboot.movie.service.Movie;
import dev.patika.movieapispringboot.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {
    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public Object retrieve(@RequestParam String movieId) {
        return movieService.getMovie(movieId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestBody MovieRequest request) {
        try {
            Movie movie = request.convertToMovie();
            return movieService.createMovie(movie);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
