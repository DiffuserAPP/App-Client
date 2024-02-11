package API

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
public interface ServerAPI {
    @POST("text")
     fun sendTextServer(@Body request: RequestPostText) : Call<ResponsePostText>
}