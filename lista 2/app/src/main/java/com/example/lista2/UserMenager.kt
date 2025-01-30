package com.example.lista2

object UserManager {

    private val users = mutableListOf(
        User("Tomasz", "tomasz"),
        User("Maria", "maria"),
        User("Weronika", "weronika"),
        User("Ireneusz", "ireneusz"),
        User("Remigiusz", "remigiusz")
    )

    fun getUsers(): List<User> = users

    fun login(username: String, password: String): User? {
        return users.find { it.username == username && it.password == password }
    }

    fun addUser(user: User): Boolean {
        val existingUser = users.find { it.username == user.username }
        return if (existingUser == null) {
            users.add(user)
            true
        } else {
            false
        }
    }
}