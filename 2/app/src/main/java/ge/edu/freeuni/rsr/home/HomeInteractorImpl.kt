package ge.edu.freeuni.rsr.home

import ge.edu.freeuni.rsr.common.FriendCredentials
import ge.edu.freeuni.rsr.common.entity.RsrResponse
import ge.edu.freeuni.rsr.network.Api
import ge.edu.freeuni.rsr.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HomeInteractorImpl : HomeContract.HomeInteractor {
    private var api: Api
    private var retrofit: Retrofit

    init {
        retrofit = RetrofitInstance.getRetrofitInstance()
        api = retrofit.create(Api::class.java)
    }

    override fun addFriend(username: String, onFinishListener: HomeContract.HomeInteractor.OnFinishListener) {
        api.confirmFriendRequest(FriendCredentials(username, "1")).enqueue(object : Callback<RsrResponse<String>> {
            override fun onFailure(call: Call<RsrResponse<String>>, t: Throwable) {
            }

            override fun onResponse(call: Call<RsrResponse<String>>, response: Response<RsrResponse<String>>) {
                onFinishListener.onFriendAdded()
            }

        })
    }

}