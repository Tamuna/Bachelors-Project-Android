package ge.edu.freeuni.rsr.tournaments.game.list

import ge.edu.freeuni.rsr.common.entity.RsrResponse
import ge.edu.freeuni.rsr.network.Api
import ge.edu.freeuni.rsr.network.RetrofitInstance
import ge.edu.freeuni.rsr.tournaments.entity.Tournament
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TournamentGameInteractorImpl : TournamentListContract.TournamentListInteractor {
    private val api: Api
    private val retrofit: Retrofit

    init {
        retrofit = RetrofitInstance.getRetrofitInstance()
        api = retrofit.create(Api::class.java)
    }

    override fun getAllTournaments(listener: TournamentListContract.TournamentListInteractor.OnFinishListener) {
        api.allTournaments.enqueue(object : Callback<RsrResponse<List<Tournament>>> {
            override fun onFailure(call: Call<RsrResponse<List<Tournament>>>, t: Throwable) {
            }

            override fun onResponse(call: Call<RsrResponse<List<Tournament>>>, response: Response<RsrResponse<List<Tournament>>>) {
                if (response.body() != null && response.body()!!.result != null) {
                    listener.onTournamentsLoaded(response.body()!!.result)
                }
            }

        })
    }
}