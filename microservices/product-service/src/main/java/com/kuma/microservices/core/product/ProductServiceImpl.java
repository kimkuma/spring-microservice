package com.kuma.microservices.core.product;

import com.kuma.api.core.product.Product;
import com.kuma.api.core.product.ProductService;
import com.kuma.util.http.ServiceUtil;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceImpl implements ProductService {

  private final ServiceUtil serviceUtil;

  public ProductServiceImpl(ServiceUtil serviceUtil) {
    this.serviceUtil = serviceUtil;
  }

  @Override
  public Product getProduct(int productId) {
    return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
  }
}
