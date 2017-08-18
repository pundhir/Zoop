package com.myprog.zoop.api;

import com.myprog.zoop.apimodel.ProductJson;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductRequestService {

    /**
     * This API will return zooPlus products information.
     * @return ProductJson
     */
    @GET("products")
    Call<ProductJson> zooPlusProducts();

    /**
     * mockyIo service.
     * @return
     */
    @GET("5996b0e2110000f20fcc458a")
    Call<ProductJson> stubService();
}
