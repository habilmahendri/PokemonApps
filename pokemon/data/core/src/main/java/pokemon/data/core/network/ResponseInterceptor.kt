package pokemon.data.core.network

import okhttp3.Interceptor
import okhttp3.Response
import pokemon.model.base.network.PokemonApiException
import java.net.ConnectException
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class ResponseInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())
            when {
                response.code == HTTP_BAD_REQUEST -> {
                    throw PokemonApiException.SystemError
                }

                response.code > HTTP_BAD_REQUEST -> {
                    throw PokemonApiException.SystemError
                }
            }
            return response
        } catch (e: UnknownHostException) {
            throw PokemonApiException.NetworkError
        } catch (e: SocketTimeoutException) {
            throw PokemonApiException.NetworkError
        } catch (e: ConnectException) {
            throw PokemonApiException.NetworkError
        } catch (e: SSLHandshakeException) {
            throw PokemonApiException.NetworkError
        }
    }
}