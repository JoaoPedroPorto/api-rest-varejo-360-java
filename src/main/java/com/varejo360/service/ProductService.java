package com.varejo360.service;

import java.util.List;

import com.varejo360.exception.ApplicationException;
import com.varejo360.response.ProductResponse;

public interface ProductService {

	List<ProductResponse> searchProducts(String keyword) throws ApplicationException;

}
