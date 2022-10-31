package ersecboom.bet.kof.domain

class GetExercisesUseCase(private val repository: Repository) {
    operator fun invoke(): List<Exercise> {
        return repository.getExercise()
    }
}