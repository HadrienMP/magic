package fr.hadrienmp.magic

import com.google.gson.GsonBuilder
import java.util.stream.Collectors

interface DeckStorage {
    fun store(deck: Deck)
}

class DeckFileStorage: DeckStorage {
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun store(deck: Deck) {
        val file = ClasspathFile("decks/full/${deck.name.string}.json")
        file.write(gson.toJson(JsonDeck(deck)))
    }
}

class JsonDeck(deck: Deck) {
    private val name = deck.name
    private val cardNumbers = deck.cardNumbers.entries
            .stream()
            .map { it -> Pair(it.key, it.value) }
            .collect(Collectors.toList())

    fun toDeck(): Deck {
        return Deck(name, mapOf(*cardNumbers.toTypedArray()))
    }
}
