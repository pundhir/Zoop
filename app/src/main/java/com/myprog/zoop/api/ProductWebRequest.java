package com.myprog.zoop.api;

import com.myprog.zoop.BuildConfig;
import com.myprog.zoop.apimodel.ProductJson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ProductWebRequest {

    /*
     * Returns retrofit builder object.
     * @return Retrofit
     */
    private Retrofit getRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    /*
     * Returns retrofit service interface API.
     * @return ProductRequestService
     */
    private ProductRequestService getRetrofitService() {
        return getRetrofitBuilder().create(ProductRequestService.class);
    }

    /*
     * Returns retrofit callback methods in case of success and failure.
     * @return Call<ProductJson>
     */
    public Call<ProductJson> getZooPlustProductDetails() {
        return getRetrofitService().zooPlusProducts();
    }

    /**
     * Stubing using Mocyio console.
     *
     * @return
     */
    private Retrofit getStubRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MOCKYIO_END_POINT)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    /**
     * Stubing using Mocyio console.
     *
     * @return
     */
    private ProductRequestService getStubRetrofitService() {
        return getStubRetrofitBuilder().create(ProductRequestService.class);
    }

    /**
     * Stubing using Mocyio console.
     *
     * @return
     */
    public Call<ProductJson> stubServiceDetails() {
        return getStubRetrofitService().stubService();
    }
}
