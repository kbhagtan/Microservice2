package com.shopping.retailer.ShoppingRetailService.repository;

import com.shopping.retailer.ShoppingRetailService.pojos.ProductInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends CrudRepository<ProductInfo,Long> {

}
