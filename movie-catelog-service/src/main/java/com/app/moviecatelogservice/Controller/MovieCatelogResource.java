package com.app.moviecatelogservice.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.moviecatelogservice.Dto.CatelogItem;
import com.app.moviecatelogservice.Dto.Movie;
import com.app.moviecatelogservice.Dto.Rating;
import com.app.moviecatelogservice.Dto.UserRatingList;



@RestController
@RequestMapping("/catelog")
public class MovieCatelogResource {
	@Autowired
	private RestTemplate restTemplae;
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("{userId}")
	public List<CatelogItem> getCatelog(@PathVariable String userId){
		UserRatingList userRatingList=restTemplae.getForObject("http://movie-rating-data-service/ratingdata/users/"+userId, UserRatingList.class);
		
		
		
		return userRatingList.getRatings().stream().map(rating->{
//			Movie movie=webClientBuilder.build().get().uri("http://localhost:8081/movies/"+rating.getMovieId()).retrieve().bodyToMono(Movie.class).block();
			Movie movie= restTemplae.getForObject( "http://movie-info-service/movies/"+rating.getMovieId(),Movie.class);
			return new CatelogItem(movie.getName(), "Desc", rating.getRating());
		}).collect(Collectors.toList());
	}
}
