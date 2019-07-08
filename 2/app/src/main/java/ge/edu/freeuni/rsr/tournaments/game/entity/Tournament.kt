package ge.edu.freeuni.rsr.tournaments.game.entity

import ge.edu.freeuni.rsr.individual.game.entity.Answer


data class Tournament(
        var id: Int,
        var tournament_name: String,
        var start_time: String,
        var expired: Boolean,
        var question_count: Int,
        var questions: List<Question>,
        var current_question: Int
)

data class Question(
        var question_content: String,
        var answers: List<Answer>
)