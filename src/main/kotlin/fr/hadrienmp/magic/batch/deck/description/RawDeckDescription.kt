package fr.hadrienmp.magic.batch.deck.description

import fr.hadrienmp.magic.domain.CardName
import fr.hadrienmp.magic.domain.DeckName

class RawDeckDescription(val deckName: DeckName, content: String) {
    private val cardNumbers = toCardNumbers(content)

    private fun toCardNumbers(content: String): Map<CardName, Int> {
        val cardDescriptions = content.split("\n")

        val cardNumbers = mutableMapOf<CardName, Int>()
        for (cardDescription in cardDescriptions) {
            val separatorIndex = cardDescription.indexOf(' ')
            val number = cardDescription.substring(0, separatorIndex).toInt()
            val cardName = CardName(cardDescription.substring(separatorIndex + 1))
            cardNumbers.put(cardName, number)
        }

        return cardNumbers.toMap()
    }

    fun cardNames(): Collection<CardName> {
        return cardNumbers.keys
    }

    fun cardNumbers(): Map<CardName, Int> {
        return cardNumbers
    }
}