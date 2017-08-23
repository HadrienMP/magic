package fr.hadrienmp.magic.batch

import fr.hadrienmp.magic.ClasspathFile
import fr.hadrienmp.magic.DeckName

interface DeckDescriptionStorage {
    fun getBy(deckName: DeckName): DeckDescription
}

class DeckDescriptionFileStorage : DeckDescriptionStorage {
    override fun getBy(deckName: DeckName): DeckDescription {
        val file = ClasspathFile("decks/descriptions/${deckName.string}.txt")
        return DeckDescription(deckName, file.read())
    }
}