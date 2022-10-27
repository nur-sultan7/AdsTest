package com.nursultan.adstest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nursultan.adstest.data.RepositoryImp
import com.nursultan.adstest.domain.Exercise
import com.nursultan.adstest.domain.GetExercisesUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repositoryImp = RepositoryImp(application)
    private val getExercisesUseCase = GetExercisesUseCase(repositoryImp)

    fun getAllExercises(): List<Exercise> {
        return getExercisesUseCase.invoke()
    }
}