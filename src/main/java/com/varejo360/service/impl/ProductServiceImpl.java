package com.varejo360.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.varejo360.constant.MessagesEnum;
import com.varejo360.dao.TableProducts;
import com.varejo360.exception.ApplicationException;
import com.varejo360.response.ProductResponse;
import com.varejo360.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Override
	public List<ProductResponse> searchProducts(String keyword) throws ApplicationException {
		if (TableProducts.getInstance().getProducts().size() == 0)
			throw new ApplicationException(MessagesEnum.FILE_NOT_FOUND.getMessage());
		if (keyword == null || keyword.trim().isEmpty())
			return TableProducts.getInstance().getProducts();
		return filter(keyword.trim().toUpperCase());
	}
	
	private List<ProductResponse> filter(String keyword) {
		List<ProductResponse> products = new ArrayList<>();
		for (ProductResponse product : TableProducts.getInstance().getProducts()) {
			if (product.getDescription().contains(keyword)) {
				ProductResponse object = new ProductResponse();
				object.setBarCode(product.getBarCode());
				object.setProductName(product.getProductName());
				object.setDescription(product.getDescription());
				object.setProbability(getPercentageByProduct(product.getDescription(), keyword));
				products.add(object);
			}
		}
		products.sort((d1, d2) -> d1.getProbability().compareTo(d2.getProbability()));
		Collections.reverse(products);
		return products;
	}
	
	private Double getPercentageByProduct(String description, String keyword) {
		String [] listKeywords = keyword.split(" ");
		String [] listDescriptions = description.split(" ");
		Integer count = 0;
		if (listKeywords.length == 0 || listDescriptions.length == 0)
			return 0.0;
		for (String key : listKeywords) {
			for (String desc : listDescriptions) {
				if (desc.equals(key)) {
					count ++;
					break;
				}
			}
		}
		return (double) ((100 * count) / listDescriptions.length);
	}
	
}