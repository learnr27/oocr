package com.bannad927.oocr.service

import com.bannad927.oocr.entity.PlateLicenseEntity
import com.bannad927.oocr.utils.JSONUtil
import org.json.JSONObject
import org.springframework.stereotype.Service

/**
 * Created by: 程彬彬
 * Date: 2018/9/3
 * Describe:
 */
@Service
class PlateLicenseService {

    fun analysisData(res: JSONObject, filePath: String): PlateLicenseEntity {
        var plateLicense = PlateLicenseEntity()
        val resultMap = JSONUtil().readObject<Map<*, *>>(res.toString(), Map::class.java)
        if (null != resultMap.get("error_code")) {
            return plateLicense
        }
        val wordsResult = resultMap.get("words_result") as Map<*, *>

        plateLicense.number = (wordsResult["number"]).toString()
        plateLicense.color = (wordsResult["color"]).toString()

        return plateLicense
    }
}