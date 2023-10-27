package br.senai.sp.jandira.api_livraria

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    // T - generic - capaz de receber objeto de qalquer coisa
    @SerializedName("data")
    var data: T? = null
)
