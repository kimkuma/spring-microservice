package com.kuma.microservices.core.review.services;

import com.kuma.api.core.review.Review;
import com.kuma.api.core.review.ReviewService;
import com.kuma.microservices.core.review.persistence.ReviewEntity;
import com.kuma.microservices.core.review.persistence.ReviewRepository;
import com.kuma.util.exceptions.InvalidInputException;
import com.kuma.util.http.ServiceUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewServiceImpl implements ReviewService {

  private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

  private final ReviewRepository repository;

  private final ReviewMapper mapper;

  private final ServiceUtil serviceUtil;

  public ReviewServiceImpl(
      ReviewRepository repository, ReviewMapper reviewMapper, ServiceUtil serviceUtil) {
    this.repository = repository;
    this.mapper = reviewMapper;
    this.serviceUtil = serviceUtil;
  }

  @Override
  public Review createReview(Review body) {
    try {
      ReviewEntity entity = mapper.apiToEntity(body);
      ReviewEntity newEntity = repository.save(entity);

      LOG.debug(
          "createReview: created a review entity: {}/{}", body.getProductId(), body.getReviewId());
      return mapper.entityToApi(newEntity);

    } catch (DataIntegrityViolationException dive) {
      throw new InvalidInputException(
          "Duplicate key, Product Id: "
              + body.getProductId()
              + ", Review Id:"
              + body.getReviewId());
    }
  }

  @Override
  public List<Review> getReviews(int productId) {

    if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

    List<ReviewEntity> entityList = repository.findByProductId(productId);
    List<Review> list = mapper.entityListToApiList(entityList);
    list.forEach(e -> e.setServiceAddress(serviceUtil.getServiceAddress()));

    LOG.debug("getReviews: response size: {}", list.size());

    return list;
  }

  @Override
  public void deleteReviews(int productId) {
    LOG.debug(
        "deleteReviews: tries to delete reviews for the product with productId: {}", productId);
    repository.deleteAll(repository.findByProductId(productId));
  }
}
