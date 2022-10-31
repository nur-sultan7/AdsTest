package ersecboom.bet.kof.domain

interface Repository {
    fun getExercise(): List<Exercise>
}