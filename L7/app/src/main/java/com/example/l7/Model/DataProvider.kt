package com.example.l7.Model

import kotlin.random.Random

object DataProvider {
    private val firstNames = listOf(
        "Adam", "Ewa", "Jan", "Anna", "Piotr", "Maria", "Tomasz", "Małgorzata", "Krzysztof", "Alicja",
        "Andrzej", "Joanna", "Michał", "Barbara", "Kamil", "Magdalena", "Robert", "Monika", "Mateusz", "Natalia"
    )

    private val lastNames = listOf(
        "Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański",
        "Woźniak", "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Kwiatkowski", "Krawczyk", "Piotrowski", "Grabowski",
        "Nowakowski", "Pawłowski"
    )

    val users = (0..40).map {
        User(
            index = Random.nextInt(100000, 1000000),
            firstName = firstNames.random(),
            lastName = lastNames.random(),
            grade = Random.nextFloat() * (5 - 2) + 2,
            year = Random.nextInt(1, 5)
        )
    }
}