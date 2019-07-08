package ge.edu.freeuni.rsr.tournaments.game.game

import ge.edu.freeuni.rsr.common.entity.RsrResponse
import ge.edu.freeuni.rsr.network.Api
import ge.edu.freeuni.rsr.network.RetrofitInstance
import ge.edu.freeuni.rsr.tournaments.game.entity.Tournament
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TournamentGameInteractorImpl : TournamentGameContract.TournamentGameInteractor {
    private var api: Api
    private var retrofit: Retrofit

    init {
        retrofit = RetrofitInstance.getRetrofitInstance()
        api = retrofit.create(Api::class.java)
    }

    override fun loadSingleTournament(id: Int, onFinishListener: TournamentGameContract.TournamentGameInteractor.OnFinishListener) {
        api.getTournament(id).enqueue(object : Callback<RsrResponse<Tournament>> {
            override fun onFailure(call: Call<RsrResponse<Tournament>>, t: Throwable) {
            }

            override fun onResponse(call: Call<RsrResponse<Tournament>>, response: Response<RsrResponse<Tournament>>) {
                if (response.body() != null && response.body()!!.result != null) {
                    onFinishListener.onDataLoaded(response.body()!!.result)
                } else {
                    onFinishListener.onTourExpired()
                }
            }

        })
    }

}