package com.example.mewwallet.repositories;

import com.example.mewwallet.network.ApiClient;
import com.example.mewwallet.network.ApiService;

public class TestRepository {
    private ApiService apiService;

    public TestRepository() {
        this.apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

}
