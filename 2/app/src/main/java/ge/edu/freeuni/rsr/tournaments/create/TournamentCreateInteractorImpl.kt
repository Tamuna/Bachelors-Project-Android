package ge.edu.freeuni.rsr.tournaments.create

import ge.edu.freeuni.rsr.common.entity.RsrResponse
import ge.edu.freeuni.rsr.network.Api
import ge.edu.freeuni.rsr.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TournamentCreateInteractorImpl : TournamentCreateContract.TournamentCreateInteractor {
    private var api: Api
    private var retrofit: Retrofit

    init {
        retrofit = RetrofitInstance.getRetrofitInstance()
        api = retrofit.create(Api::class.java)
    }

    override fun saveTournament(name: String, startTime: String, userId: Int, listener: TournamentCreateContract.TournamentCreateInteractor.OnFinishListener) {
        api.saveTourHeader(TournamentConfigBody(name, userId, startTime)).enqueue(object : Callback<RsrResponse<Int>> {
            override fun onResponse(call: Call<RsrResponse<Int>>, response: Response<RsrResponse<Int>>) {
                if (response.body() != null && response.body()!!.error == null) {
                    listener.OnTournamentSaved()
                } else {
                    listener.OnError()
                }
            }

            override fun onFailure(call: Call<RsrResponse<Int>>, t: Throwable) {

            }
        });
    }

}