package fr.hadrienmp.magic.providers

import com.google.gson.GsonBuilder
import fr.hadrienmp.magic.domain.DeckDescription
import fr.hadrienmp.magic.domain.DeckName
import fr.hadrienmp.magic.domain.DeckStorage
import fr.hadrienmp.magic.lib.ClasspathFile
import java.util.stream.Collectors

class FileDeckStorage : DeckStorage {
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun get(deckName: DeckName): DeckDescription {
        val file = getFile(deckName)
        val jsonDeck = gson.fromJson(file.read(), JsonDeckDescription::class.java)
        return jsonDeck.toDeck()
    }

    override fun store(deckDescription: DeckDescription) {
        val file = getFile(deckDescription.name)
        file.write(gson.toJson(JsonDeckDescription(deckDescription)))
    }

    private fun getFile(deckName: DeckName) =
            ClasspathFile("decks/full/${deckName.string}.json")
}

class JsonDeckDescription(deckDescription: DeckDescription) {

    private val name = deckDescription.name
    private val cardNumbers = deckDescription.cardNumbers.entries
            .stream()
            .map { it -> Pair(it.key, it.value) }
            .collect(Collectors.toList())

    fun toDeck(): DeckDescription {
        return DeckDescription(name, mapOf(*cardNumbers.toTypedArray()))
    }
}
