package com.nursultan.adstest.domain

interface Repository {
    fun getExercise(): List<Exercise>
}