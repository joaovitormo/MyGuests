package com.joaovitormo.myguests.repository

//O construtor precisa ser privado para que não tenha muitas instancias da mesma classe
class GuestRepository private constructor() {

    //Singleton - Acesso as intancias da nossa classe - controlar o nº de instancias
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }

    }
}