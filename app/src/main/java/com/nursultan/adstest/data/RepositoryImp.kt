package com.nursultan.adstest.data

import android.content.Context
import com.nursultan.adstest.R
import com.nursultan.adstest.domain.Exercise
import com.nursultan.adstest.domain.Repository

class RepositoryImp(private val context: Context) : Repository {
    override fun getExercise(): List<Exercise> {
        val listOfExercise = mutableListOf<Exercise>().apply {
            addAll(
                listOf(
                    Exercise(
                        R.drawable.bench_press,
                        "Bench press",
                        context.getString(R.string.benchPressDes)
                    ),
                    Exercise(
                        R.drawable.triceps_dip,
                        "Triceps dip",
                        context.getString(R.string.tricepsDipDes)
                    ),
                    Exercise(
                        R.drawable.incline_dumbbell_press,
                        "Incline dumbbell press",
                        context.getString(R.string.inclinePressDes)
                    ),
                    Exercise(
                        R.drawable.incline_dumbbell_flye,
                        "Incline dumbbell flye",
                        context.getString(R.string.inclineFlyeDes)
                    ),
                    Exercise(
                        R.drawable.triceps_extension,
                        "Triceps extension",
                        context.getString(R.string.tricepsExtensionDes)
                    ),
                    Exercise(
                        R.drawable.pull_up,
                        "Pull-up",
                        context.getString(R.string.pullUpDes)
                    ),
                    Exercise(
                        R.drawable.bent_over_row,
                        "Bent-over row",
                        context.getString(R.string.bentOverRowDes)
                    ),
                    Exercise(
                        R.drawable.chin_up,
                        "Chin-up",
                        context.getString(R.string.chinUpDes)
                    )
                )
            )
        }
        return listOfExercise
    }
}