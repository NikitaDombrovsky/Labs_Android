package com.example.parser.Models.Jackson;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("active")
    ACTIVE,
    @SerializedName("disabled")
    DISABLED
}