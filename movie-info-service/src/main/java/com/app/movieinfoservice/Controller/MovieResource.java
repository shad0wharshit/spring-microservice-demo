package com.app.movieinfoservice.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.movieinfoservice.Dto.Movie;
import com.app.movieinfoservice.Dto.Rating;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		return new Movie(movieId, "Star Wars");
	}

}
