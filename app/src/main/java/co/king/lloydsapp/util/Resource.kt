package co.king.lloydsapp.util

sealed class Resource<T>(data: T?, errorMessage: String?) {
    data class Success<T>(val data: T): Resource<T>(data = data, errorMessage = null)
    data class Failure<T>(val data: T?, val errorMessage: String): Resource<T>(data, errorMessage)
    data class Loading<T>(val isLoading: Boolean): Resource<T>(data = null, errorMessage = null)
}