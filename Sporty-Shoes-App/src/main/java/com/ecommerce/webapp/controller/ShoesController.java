package com.ecommerce.webapp.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.webapp.entity.Shoes;
import com.ecommerce.webapp.exception.ProductNotFound;
import com.ecommerce.webapp.repository.ShoesRepository;

@RestController
public class ShoesController {
	@Autowired
	ShoesRepository shoesRepo;

	// GET all product
	@GetMapping("/products")
	public List<Shoes> getAllProduct() {
		return shoesRepo.findAll();
	}

	// GET product by id
	@GetMapping("/products/{id}")
	public Shoes getShoesById(@PathVariable(value = "id") long productId) {
		return this.shoesRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFound("Product Not found with id " + productId));
	}

	// create a product
	@PostMapping("/addproducts")
	public Shoes addProduct(@RequestBody Shoes product) {
		return this.shoesRepo.save(product);
	}

	// update a product
	@PutMapping("/updateproducts/{id}")
	public Shoes updateProduct(@RequestBody Shoes product, @PathVariable(value = "id") long productId) {
		// 1. find a record / product
		Shoes fetchedProduct = this.shoesRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFound("Product Not found wit id " + productId));
		// 2 . set new values
		fetchedProduct.setName(product.getName());
		fetchedProduct.setBrand(product.getBrand());
		fetchedProduct.setDescription(product.getDescription());
		fetchedProduct.setPrice(product.getPrice());
		fetchedProduct.setCreatedAt(product.getCreatedAt());
		// 3.save a product

		return this.shoesRepo.save(fetchedProduct);
	}

	// delete a product
	@DeleteMapping("/deleteproducts/{id}")
	public void deleteProduct(@PathVariable(value = "id") long productId) {
		// 1. find a record / product
		Shoes fetchedProduct = this.shoesRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFound("Product Not found wit id " + productId));
		this.shoesRepo.delete(fetchedProduct);
	}

	// Filter product by Purchase Date
	@GetMapping("/shoes/createdat")
	public ResponseEntity<List<Shoes>> getShoesByCreatedDate(@RequestParam Date startDate, @RequestParam Date endDate) {
		return new ResponseEntity<List<Shoes>>(shoesRepo.findByCreatedAtBetween(startDate, endDate), HttpStatus.OK);
	}

	// Filter product by Price
	@GetMapping("/shoes/price")
	public ResponseEntity<List<Shoes>> getShoesByPrice(@RequestParam BigDecimal startPrice,
			@RequestParam BigDecimal endPrice) {
		return new ResponseEntity<List<Shoes>>(shoesRepo.findByPriceBetween(startPrice, endPrice), HttpStatus.OK);
	}

	// Filter product by Brand
	@GetMapping("/shoes/brand")
	public ResponseEntity<List<Shoes>> getShoesByBrand(@RequestParam String brand) {
		return new ResponseEntity<List<Shoes>>(shoesRepo.findByBrand(brand), HttpStatus.OK);
	}

	// filter product by Brand and Price
	@GetMapping("/shoes/brandandprice")
	public ResponseEntity<List<Shoes>> getShoesByBrandAndPrice(@RequestParam String brand,
			@RequestParam BigDecimal price) {
		return new ResponseEntity<List<Shoes>>(shoesRepo.findByBrandAndPrice(brand, price), HttpStatus.OK);
	}

	// filter product by Name or Brand or Price
	@GetMapping("/shoes/nameorbrandorprice")
	public ResponseEntity<List<Shoes>> getShoesByNameOrBrandOrPrice(@RequestParam String name,
			@RequestParam String brand, @RequestParam BigDecimal price) {
		return new ResponseEntity<List<Shoes>>(shoesRepo.findByNameOrBrandOrPrice(name, brand, price), HttpStatus.OK);
	}

}
