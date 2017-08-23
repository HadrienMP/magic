package fr.hadrienmp.magic.batch

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import feign.Feign
import feign.QueryMap
import feign.RequestLine
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import fr.hadrienmp.magic.Card
import fr.hadrienmp.magic.CardName
import fr.hadrienmp.magic.SetCode
import java.net.URL

interface ReferenceLibrary {
    fun getCard(cardName: CardName, setCode: SetCode): Card
}

class API : ReferenceLibrary {
    private val mtgApi = Feign.builder()
            .encoder(GsonEncoder())
            .decoder(GsonDecoder())
            .logger(Slf4jLogger())
            .client(OkHttpClient())
            .target(MtgApi::class.java, "https://api.magicthegathering.io/v1")

    override fun getCard(cardName: CardName, setCode: SetCode): Card {
        val queryMap = mapOf(Pair("string", cardName.string), Pair("set", setCode.string))
        val card = getApiCard(queryMap)
        val strUrl = card.get("imageUrl").toString().replace("\"", "")
        return Card(cardName, URL(strUrl))
    }

    private fun getApiCard(queryMap: Map<String, String>): JsonObject {
        val cards: JsonArray = mtgApi.getCard(queryMap).get("cards") as JsonArray
        return cards[0] as JsonObject
    }
}

interface MtgApi {
    @RequestLine("GET /cards")
    fun getCard(@QueryMap queryMap: Map<String, String>): JsonObject
}