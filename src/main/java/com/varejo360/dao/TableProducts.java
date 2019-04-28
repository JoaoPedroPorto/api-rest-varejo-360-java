package com.varejo360.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.varejo360.constant.ApiMapping;
import com.varejo360.response.ProductResponse;

public class TableProducts {
	
	private static TableProducts instance = new TableProducts();
	private List<ProductResponse> products = new ArrayList<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(TableProducts.class);
	
	
	private TableProducts() {
		products = mountListOfProducts();
	}
	
	private List<ProductResponse> mountListOfProducts() {
		try {
			Path path = Paths.get(ApiMapping.FILE);
			if (!Files.exists(path))
				return new ArrayList<>();
			String line = null;
			Scanner scanner = null;
			int index = 0;
			int countLine = 0;
			List<ProductResponse> products = new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader(ApiMapping.FILE));
			while ((line = reader.readLine()) != null) {
				ProductResponse product = new ProductResponse();
				scanner = new Scanner(line);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					String data = scanner.next();
					if (index == 0)
						product.setBarCode(data);
					else if (index == 1)
						product.setProductName(data);
					else if (index == 2)
						product.setDescription(data);
					index ++;
				}
				index = 0;
				if (countLine > 0)
					products.add(product);
				countLine ++;
			}
			reader.close();
			return products;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return products;
	}
	
	public static TableProducts getInstance() {
		return instance;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

}
