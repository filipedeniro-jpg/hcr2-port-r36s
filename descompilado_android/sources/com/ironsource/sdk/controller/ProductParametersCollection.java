package com.ironsource.sdk.controller;

import com.ironsource.sdk.data.ProductParameters;
import com.ironsource.sdk.data.SSAEnums;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ProductParametersCollection {
    private Map<SSAEnums.ProductType, ProductParameters> mProductParameters = new HashMap();

    public ProductParameters setProductParameters(SSAEnums.ProductType productType, String str, String str2) {
        ProductParameters productParameters = new ProductParameters(str, str2);
        this.mProductParameters.put(productType, productParameters);
        return productParameters;
    }

    public ProductParameters getProductParameters(SSAEnums.ProductType productType) {
        if (productType != null) {
            return this.mProductParameters.get(productType);
        }
        return null;
    }
}
