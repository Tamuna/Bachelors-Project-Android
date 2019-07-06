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
        api.saveTourHeader(
                TournamentConfigBody(name, userId, startTime))
                .enqueue(object : Callback<RsrResponse<Int>> {
                    override fun onResponse(call: Call<RsrResponse<Int>>, response: Response<RsrResponse<Int>>) {
                        if (response.body() != null && response.body()!!.error == null) {
                            listener.onTournamentSaved(response.body()!!.result)
                        } else {
                            listener.onError()
                        }
                    }

                    override fun onFailure(call: Call<RsrResponse<Int>>, t: Throwable) {

                    }
                })
    }

    override fun saveSingleQuestion(question: String, answers: List<String>, userId: Int, tourId: Int, listener: TournamentCreateContract.TournamentCreateInteractor.OnFinishListener) {
        api.saveTourHeader(
                TournamentConfigBody(user_id = userId, question_content = question, answers = answers, tour_id = tourId))
                .enqueue(object : Callback<RsrResponse<Int>> {
                    override fun onResponse(call: Call<RsrResponse<Int>>, response: Response<RsrResponse<Int>>) {
                        if (response.body() != null && response.body()!!.error == null) {
                            listener.onQuestionAddedToTournament()
                        } else {
                            listener.onError()
                        }
                    }

                    override fun onFailure(call: Call<RsrResponse<Int>>, t: Throwable) {

                    }
                })
    }


}