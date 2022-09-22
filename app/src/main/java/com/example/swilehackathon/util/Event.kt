package com.example.swilehackathon.util

open class Event<out T>(private val _content: T? = null) {
    private var hasBeenHandled = false

    val content: T?
        get() {
            return if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                _content
            }
        }

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            _content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = _content!!
}
