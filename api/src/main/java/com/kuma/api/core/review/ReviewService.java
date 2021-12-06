package com.kuma.api.core.review;

import com.kuma.api.core.review.Review;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewService {

  @PostMapping(
      value = "/review",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  Review createReview(@RequestBody Review body);

  @GetMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);

  @DeleteMapping(value = "/review")
  void deleteReviews(@RequestParam(value = "productId", required = true) int productId);
}
