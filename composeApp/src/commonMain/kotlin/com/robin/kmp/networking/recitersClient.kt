package com.robin.kmp.networking

import com.robin.kmp.model.Reciters
import com.robin.kmp.util.NetworkError
import com.robin.kmp.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class RecitersClient (
    private val httpClient: HttpClient
)
{
    suspend fun getReciters(): Result<Reciters,NetworkError>{
        val response = try {
            httpClient.get(urlString = "https://listenonlinequran.com/apps/api/quran_reciters_api.json"){
              //  parameter("language","en")
            }
        }catch (e: UnresolvedAddressException){
            return Result.Error(NetworkError.NO_INTERNET)
        }catch (e: SerializationException){
            return Result.Error(NetworkError.SERIALIZATION)

        }

        return when(response.status.value){
            in 200..299 -> {
                val reciter = response.body<Reciters>()
                Result.Success(reciter)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)

        }
        }


}