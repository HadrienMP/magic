package fr.hadrienmp.magic.batch.deck.description

import fr.hadrienmp.magic.domain.DeckName
import fr.hadrienmp.magic.lib.ClasspathFile

interface RawDeckDescriptionStorage {
    fun getBy(deckName: DeckName): RawDeckDescription
}

class RawDeckDescriptionFileStorage : RawDeckDescriptionStorage {
    override fun getBy(deckName: DeckName): RawDeckDescription {
        val file = ClasspathFile("decks/raw/${deckName.string}.txt")
        return RawDeckDescription(deckName, file.read())
    }
}