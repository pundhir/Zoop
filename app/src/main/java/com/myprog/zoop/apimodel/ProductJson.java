package com.myprog.zoop.apimodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by S711256 on 18/08/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductJson {
    public List<Product> products;
}
