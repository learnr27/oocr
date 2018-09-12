package com.bannad927.oocr.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

/**
 * Created by: 程彬彬
 * Date: 2018/8/27
 * Describe:
 */
class JSONUtil {

    private val OBJECT_MAPPER = ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    @Throws(JsonProcessingException::class)
    fun toJSONString(source: Any): String {
        val writer = OBJECT_MAPPER.writer()
        val jsonString = writer.writeValueAsString(source)
        return jsonString
    }

    @Throws(IOException::class)
    fun readTree(jsonString: String): JsonNode {
        val objectReader = OBJECT_MAPPER.reader()
        return objectReader.readTree(jsonString)
    }

    @Throws(IOException::class)
    fun <T> readObject(jsonString: String, type: Class<*>): T {
        val objectReader = OBJECT_MAPPER.readerFor(type)
        return objectReader.readValue<T>(jsonString)
    }
}