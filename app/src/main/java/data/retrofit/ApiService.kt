
package data.retrofit
import data.response.Follow
import data.response.GithubDetail
import data.response.GithubResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getListUsers(@Query("q") page: String): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<GithubDetail>

    @GET("users/{username}/followers")
    fun getFollowersList(@Path("username") username: String): Call<List<Follow>>

    @GET("users/{username}/following")
    fun getFollowingList(@Path("username") username: String): Call<List<Follow>>

}