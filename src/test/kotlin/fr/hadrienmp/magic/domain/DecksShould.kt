package fr.hadrienmp.magic.domain

import fr.hadrienmp.magic.providers.FileDeckStorage
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DecksShould {
    @Test fun `contain the list of cards`() {
        val deckStorage = FileDeckStorage()
        val decks = Decks(deckStorage)

        val deck = decks.get(DeckName("Demonic"))

        assertThat(deck.cards).hasSize(120)
    }
}

