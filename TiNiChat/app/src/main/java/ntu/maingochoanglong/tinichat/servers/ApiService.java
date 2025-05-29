package ntu.maingochoanglong.tinichat.servers;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("send")
    Call<String> sendMessage(@Body RequestBody messageBody);
}
