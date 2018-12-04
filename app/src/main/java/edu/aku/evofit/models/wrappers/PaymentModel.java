package edu.aku.evofit.models.wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.evofit.managers.retrofit.GsonFactory;

/**
 * Created by hamza.ahmed on 3/6/2018.
 */

public class PaymentModel {

    @Expose
    @SerializedName("Signature")
    private String signature;
    @Expose
    @SerializedName("Parameters")
    private Parameters parameters;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}
