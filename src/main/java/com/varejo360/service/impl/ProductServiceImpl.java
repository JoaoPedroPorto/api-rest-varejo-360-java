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
import com.varejo360.util.ApplicationUtil;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Override
	public List<ProductResponse> searchProducts(String keyword) throws ApplicationException {
		if (TableProducts.getInstance().getProducts().size() == 0)
			throw new ApplicationException(MessagesEnum.FILE_NOT_FOUND.getMessage());
		if (keyword == null || keyword.trim().isEmpty())
			return listAllProducts();
		return filter(ApplicationUtil.removeCaracterSpecial(keyword.trim().toUpperCase()));
	}
	
	private List<ProductResponse> listAllProducts() {
		List<ProductResponse> products = new ArrayList<>();
		// RETORNA SOMENTE OS PRODUTOS FIXOS SEM A VARIACAO DE DESCRICOES
		TableProducts.getInstance().getProducts().forEach(product->{
			if (!products.contains(product)) {
				products.add(product);
			}
		});
		return products;
	}
	
	private List<ProductResponse> filter(String keyword) {
		List<ProductResponse> resultProducts = new ArrayList<>();
		// REALIZAR A BUSCA DO PRODUTO PELA DESCRICAO
		TableProducts.getInstance().getProducts().forEach(product->{
			if (product.getDescription().contains(keyword) || 
					product.getBarCode().equals(keyword) || 
					product.getProductName().contains(keyword)) {
				ProductResponse object = new ProductResponse();
				object.setBarCode(product.getBarCode());
				object.setProductName(product.getProductName());
				object.setDescription(product.getDescription());
				object.setProbability(getPercentageByProduct(product.getDescription(), keyword));
				resultProducts.add(object);
			}
		});
		// ORDENACAO DE PROBABILIDADE
		resultProducts.sort((d1, d2) -> d1.getProbability().compareTo(d2.getProbability()));
		Collections.reverse(resultProducts);
		// RETORNO SOMENTE UM PRODUTO EM CIMA DAS VARIADAS DESCRICOES
		List<ProductResponse> products = new ArrayList<>();
		resultProducts.forEach(product->{
			if (!products.contains(product)) {
				if (product.getBarCode().equals(keyword)) {
					product.setProbability(100.0);
				}
				products.add(product);
			}
		});
		return products;
	}
	
	// REALIZA O CALCULO DA PORCENTAGEM EM CIMA DA DESCRICAO DO PRODUTO
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