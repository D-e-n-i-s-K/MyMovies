package com.example.mymovies.domain.impl

import com.example.mymovies.domain.api.MoviesInteractor
import com.example.mymovies.domain.api.MoviesRepository

class MoviesInteractorImpl( private val  repository: MoviesRepository) : MoviesInteractor {
    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        val t = Thread {
            consumer.consume(repository.searchMovies(expression))
        }
        t.start()
    }
}