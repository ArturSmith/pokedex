package com.example.pokedex.domain.extensions

inline fun <T, R> Result<T>.flatMap(transform: (value: T) -> Result<R>): Result<R> {
    return fold(
        onSuccess = { value -> transform(value) },
        onFailure = { exception -> Result.failure(exception) }
    )
}