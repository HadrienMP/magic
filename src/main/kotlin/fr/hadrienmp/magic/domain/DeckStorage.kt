package fr.hadrienmp.magic.domain

interface DeckStorage {
    fun store(deckDescription: DeckDescription)
    fun get(deckName: DeckName): DeckDescription
}