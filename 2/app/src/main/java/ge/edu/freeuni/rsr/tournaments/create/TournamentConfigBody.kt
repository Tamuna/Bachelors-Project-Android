package ge.edu.freeuni.rsr.tournaments.create

class TournamentConfigBody(
        private val name: String = "",
        private val user_id: Int = 0,
        private val start_time: String = "",
        private val answers: List<String>? = null,
        private val question_content: String = "",
        private val tour_id: Int = 0,
        private val points: Int = 0,
        private val player_id: Int = -1
)