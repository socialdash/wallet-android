package com.storiqa.storiqawallet.network.network_responses


data class GetTokenResponseByProvider(val data : TokenDataByProvider?, val errors : List<GetTokenError>?)

//data
data class TokenDataByProvider(val getJWTByProvider : GetJWTByProvider)

data class GetJWTByProvider(val token : String)