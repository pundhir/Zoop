package com.myprog.zoop.ui;

import com.myprog.zoop.api.ProductWebRequest;
import com.myprog.zoop.apimodel.Product;
import com.myprog.zoop.apimodel.ProductJson;
import com.myprog.zoop.viewmodel.ProductModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductController {

    private ProductResultListener resultListener;

    ProductController(ProductResultListener listener) {
        resultListener = listener;
    }


    public interface ProductResultListener {
        void provideProductsResult(List<ProductModel> list);
    }

    /**
     * This method is used to call web request, and get appropriate result.
     */
    public void requestProductService() {
        Call<ProductJson> request = new ProductWebRequest().getZooPlustProductDetails();
        request.enqueue(new Callback<ProductJson>() {
            @Override
            public void onResponse(Call<ProductJson> call, Response<ProductJson> response) {
                resultListener.provideProductsResult(createProductViewModel(response.body()));
            }

            @Override
            public void onFailure(Call<ProductJson> call, Throwable t) {
                resultListener.provideProductsResult(null);
            }
        });
    }

    /**
     * This method is used to call web request, and get appropriate result.
     */
    public void stubRequestProductService() {
        Call<ProductJson> request = new ProductWebRequest().stubServiceDetails();
        request.enqueue(new Callback<ProductJson>() {
            @Override
            public void onResponse(Call<ProductJson> call, Response<ProductJson> response) {
                resultListener.provideProductsResult(createProductViewModel(response.body()));
            }

            @Override
            public void onFailure(Call<ProductJson> call, Throwable t) {
                resultListener.provideProductsResult(null);
            }
        });
    }

    /**
     * This method is used to create view model from json response.
     *
     * @param respJson server json response.
     * @return list of near by Products.
     */
    public List<ProductModel> createProductViewModel(ProductJson respJson) {
        List<ProductModel> ProductList = new ArrayList<>();
        List<Product> results = respJson.products;
        for (Product res : results) {
            ProductModel pm = new ProductModel();
            pm.id = res.id;
            pm.name = res.name;
            pm.price = res.price;
            pm.currency = res.currency;

            ProductList.add(pm);
        }
        return ProductList;
    }
}
