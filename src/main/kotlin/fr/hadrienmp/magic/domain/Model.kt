package fr.hadrienmp.magic.domain

import java.net.URL


data class Deck (val name: DeckName, val cards: Collection<Card>)
data class DeckName(val string: String)
data class DeckDescription(val name: DeckName, val cardNumbers: Map<Card, Number>)
data class Card(val name: CardName, val image: URL)
data class CardName(val string: String)
data class SetCode(val string: String) {
    init {
        if (string.length != 3) {
            throw IllegalArgumentException("A set code must be 3 characters long")
        }
    }
}


