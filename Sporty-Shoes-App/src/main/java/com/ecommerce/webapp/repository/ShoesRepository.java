package com.ecommerce.webapp.repository;

import java.math.BigDecimal;
import java.util.List;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.webapp.entity.Shoes;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long> {

	List<Shoes> findByCreatedAtBetween(Date startDate, Date endDate);

	List<Shoes> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

	List<Shoes> findByBrand(String name);

	List<Shoes> findByPrice(BigDecimal price);

	List<Shoes> findByBrandAndPrice(String brand, BigDecimal Price);

	List<Shoes> findByNameOrBrandOrPrice(String name, String brand, BigDecimal price);

}
