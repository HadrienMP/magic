package fr.hadrienmp.magic.domain

import fr.hadrienmp.magic.providers.FileDeckStorage

class Decks(val deckStorage: FileDeckStorage) {
    fun get(deckName: DeckName): Deck {
        val (_, cardNumbers) = deckStorage.get(deckName)
        val cards = mutableListOf<Card>()
        for ((card, number) in cardNumbers) {
            cards.addAll((1..number.toInt()).map { card })
            for (i in 1..number.toInt()) {
                cards.add(card)
            }
        }
        return Deck(deckName, cards)
    }

}