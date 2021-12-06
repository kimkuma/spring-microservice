package com.kuma.api.core.recommendation;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface RecommendationService {

  @PostMapping(
      value = "/recommendation",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  Recommendation createRecommendation(@RequestBody Recommendation body);

  @GetMapping(value = "/recommendation", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Recommendation> getRecommendations(
      @RequestParam(value = "productId", required = true) int productId);

  @DeleteMapping(value = "/recommendation")
  void deleteRecommendations(@RequestParam(value = "productId", required = true) int productId);
}
