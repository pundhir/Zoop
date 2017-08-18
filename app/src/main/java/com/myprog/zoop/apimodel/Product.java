package com.myprog.zoop.apimodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    public String id;
    public String name;
    public String price;
    public String currency;
}
