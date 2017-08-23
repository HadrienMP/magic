package fr.hadrienmp.magic.batch

import fr.hadrienmp.magic.*

fun main(args: Array<String>) {

    val deckStorage = DeckFileStorage()
    val deckFactory = DeckFactory(DeckDescriptionFileStorage(), API())

    val demonic = deckFactory.getDeck(DeckName("Demonic"))
    deckStorage.store(demonic)

    val divine = deckFactory.getDeck(DeckName("Divine"))
    deckStorage.store(divine)
}

class DeckFactory(private val storage: DeckDescriptionStorage, private val referenceLibrary: ReferenceLibrary) {

    fun getDeck(deckName: DeckName): Deck {
        val deckDescription = storage.getBy(deckName)
        return toDeck(deckDescription)
    }

    private fun toDeck(deckDescription: DeckDescription): Deck {
        val cardNumbers = mutableMapOf<Card, Int>()
        for ((cardName, number) in deckDescription.cardNumbers()) {
            val card = referenceLibrary.getCard(cardName)
            cardNumbers.put(card, number)
        }
        return Deck(deckDescription.deckName, cardNumbers)
    }
}

