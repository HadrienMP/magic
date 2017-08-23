package fr.hadrienmp.magic

import java.net.URL

data class Deck(val name: DeckName, val cardNumbers: Map<Card, Number>)
data class DeckName(val string: String)
data class Card(val name: CardName, val image: URL)
data class CardName(val string: String)


