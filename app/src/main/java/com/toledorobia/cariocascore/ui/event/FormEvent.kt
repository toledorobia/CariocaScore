package com.toledorobia.cariocascore.ui.event

import kotlin.random.Random

sealed class FormEvent {
    data class Submitting(val loading: Boolean): FormEvent()
    data class Error(val message: String, val type: Int = 0): FormEvent()
    data class Success(val message: String, val finish: Boolean = false): FormEvent()
    data class Delete(val message: String, val finish: Boolean = true): FormEvent()

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}
