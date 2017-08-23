package fr.hadrienmp.magic

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DeckDescriptionShould {

    private val storage = DeckDescriptionFileStorage()

    @Test fun `know the names of cards it holds`() {
        val deck = storage.getBy(DeckName("Demonic"))

        val cardNames = deck.cardNames()

        assertThat(cardNames).contains(
                CardName("Swamp"),
                CardName("Demon's Jester"),
                CardName("Demon's Horn"))
    }

    @Test fun `know the number of cards it holds by card`() {
        val deck = storage.getBy(DeckName("Demonic"))

        val numberOfSwamps = deck.cardNumbers()[CardName("Swamp")]

        assertThat(numberOfSwamps).isEqualTo(24)
    }
}