package ge.edu.freeuni.rsr.groupchat.addfriend

import ge.edu.freeuni.rsr.auth.entity.Credentials
import ge.edu.freeuni.rsr.common.entity.RsrResponse
import ge.edu.freeuni.rsr.common.entity.User
import ge.edu.freeuni.rsr.groupchat.configuration.NotificationBody
import ge.edu.freeuni.rsr.network.Api
import ge.edu.freeuni.rsr.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AddFriendInteractorImpl : AddFriendContract.AddFriendInteractor {

    private var api: Api
    private var retrofit: Retrofit

    init {
        retrofit = RetrofitInstance.getRetrofitInstance()
        api = retrofit.create(Api::class.java)
    }

    override fun loadUsers(s: String, onFinishListener: AddFriendContract.AddFriendInteractor.OnFinishListener) {
        api.getFilteredUsers(Credentials(s)).enqueue(object : Callback<RsrResponse<List<User>>> {
            override fun onFailure(call: Call<RsrResponse<List<User>>>, t: Throwable) {
            }

            override fun onResponse(call: Call<RsrResponse<List<User>>>, response: Response<RsrResponse<List<User>>>) {
                if (response.body() != null && response.body()!!.result != null) {
                    onFinishListener.OnDataLoaded(response.body()!!.result)
                }
            }

        })
    }

    override fun sendFriendRequest(message: String, friendUsername: String, onFinishListener: AddFriendContract.AddFriendInteractor.OnFinishListener) {
        api.sendFriendRequest(NotificationBody(message, friendUsername)).enqueue(object : Callback<RsrResponse<String>> {
            override fun onFailure(call: Call<RsrResponse<String>>, t: Throwable) {
            }

            override fun onResponse(call: Call<RsrResponse<String>>, response: Response<RsrResponse<String>>) {
                if (response.body() != null) {
                    if (response.body()!!.error != null) {
                        onFinishListener.onFriendRequestSent("თქვენ უკვე მეგობრები ხართ!")
                    } else {
                        onFinishListener.onFriendRequestSent("მეგობრობის მოთხოვნა გაგზავნილია!")
                    }
                }
            }

        })
    }
}