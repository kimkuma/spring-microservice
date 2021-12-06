package com.kuma.api.core.product;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductService {

  @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
  Product getProduct(@PathVariable int productId);

  @PostMapping(
      value = "/product",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  Product createProduct(@RequestBody Product body);

  @DeleteMapping(value = "/product/{productId}")
  void deleteProduct(@PathVariable int productId);
}
