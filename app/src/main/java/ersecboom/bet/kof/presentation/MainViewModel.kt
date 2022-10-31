package ersecboom.bet.kof.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ersecboom.bet.kof.data.RepositoryImp
import ersecboom.bet.kof.domain.Exercise
import ersecboom.bet.kof.domain.GetExercisesUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repositoryImp = RepositoryImp(application)
    private val getExercisesUseCase = GetExercisesUseCase(repositoryImp)

    fun getAllExercises(): List<Exercise> {
        return getExercisesUseCase.invoke()
    }

}