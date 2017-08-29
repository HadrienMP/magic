package fr.hadrienmp.magic.batch.deck.description

import fr.hadrienmp.magic.domain.Card
import fr.hadrienmp.magic.domain.DeckDescription
import fr.hadrienmp.magic.domain.DeckName
import fr.hadrienmp.magic.domain.SetCode
import fr.hadrienmp.magic.providers.*

fun main(args: Array<String>) {

    val deckStorage = FileDeckStorage()
    val deckFactory = DeckDescriptionFactory(RawDeckDescriptionFileStorage(), API())

    val demonic = deckFactory.getDeck(DeckName("Demonic"))
    deckStorage.store(demonic)

    val divine = deckFactory.getDeck(DeckName("Divine"))
    deckStorage.store(divine)
}

class DeckDescriptionFactory(private val storageRaw: RawDeckDescriptionStorage, private val referenceLibrary: ReferenceLibrary) {

    fun getDeck(deckName: DeckName): DeckDescription {
        val deckDescription = storageRaw.getBy(deckName)
        return toDeck(deckDescription)
    }

    private fun toDeck(rawDeckDescription: RawDeckDescription): DeckDescription {
        val cardNumbers = mutableMapOf<Card, Int>()
        for ((cardName, number) in rawDeckDescription.cardNumbers()) {
            val card = referenceLibrary.getCard(cardName, SetCode("DDC"))
            cardNumbers.put(card, number)
        }
        return DeckDescription(rawDeckDescription.deckName, cardNumbers)
    }
}

