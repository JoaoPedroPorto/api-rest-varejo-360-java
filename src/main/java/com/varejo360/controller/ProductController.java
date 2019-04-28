package com.varejo360.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varejo360.constant.ApiMapping;
import com.varejo360.constant.MessagesEnum;
import com.varejo360.exception.ApplicationException;
import com.varejo360.request.SearchRequest;
import com.varejo360.response.ProductResponse;
import com.varejo360.response.Response;
import com.varejo360.service.ProductService;

@RestController
@RequestMapping(ApiMapping.PRODUCT)
public class ProductController {
	
	@Autowired
	private ProductService productService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "")
	public ResponseEntity<Response<List<ProductResponse>>> searchProduct(@Valid @RequestBody SearchRequest search) throws ApplicationException {
		Response<List<ProductResponse>> res = new Response<List<ProductResponse>>();
		try {
			List<ProductResponse> products = productService.searchProducts(search.getKeyword());
			res.setData(products);
			return ResponseEntity.ok(res);
		} catch (ApplicationException e) {
			res.setError(e.getMessage());
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(res);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			res.setError(MessagesEnum.ERRO_SOLICITATION.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
}
