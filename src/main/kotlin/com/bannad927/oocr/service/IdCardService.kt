package com.bannad927.oocr.service

import com.bannad927.oocr.entity.IdCardEntity
import com.bannad927.oocr.repository.IdCardRepository
import com.bannad927.oocr.utils.JSONUtil
import org.json.JSONObject
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by: 程彬彬
 * Date: 2018/8/31
 * Describe:
 */
@Service
class IdCardService {

    @Resource
    lateinit var idCardRepository: IdCardRepository

    fun analysisData(res: JSONObject, filePath: String): IdCardEntity {
        var idCard = IdCardEntity()
        val resultMap = JSONUtil().readObject<Map<*, *>>(res.toString(), Map::class.java)
        if (null != resultMap.get("error_code")) {
            return idCard
        }
        val wordsResult = resultMap.get("words_result") as Map<*, *>

        for (keyStr in wordsResult.keys) {
            if ("住址" == keyStr) {
                idCard.address = (wordsResult["住址"] as Map<*, *>)["words"].toString()
            }
            if ("公民身份号码" == keyStr) {
                idCard.cardNo = (wordsResult["公民身份号码"] as Map<*, *>)["words"].toString()
            }
            if ("出生" == keyStr) {
                idCard.birthday = (wordsResult["出生"] as Map<*, *>)["words"].toString()
            }
            if ("姓名" == keyStr) {
                idCard.name = (wordsResult["姓名"] as Map<*, *>)["words"].toString()
            }
            if ("性别" == keyStr) {
                idCard.gender = (wordsResult["性别"] as Map<*, *>)["words"].toString()
            }
            if ("民族" == keyStr) {
                idCard.nation = (wordsResult["民族"] as Map<*, *>)["words"].toString()
            }

        }
        idCard.filePath = filePath
        idCardRepository.save(idCard)
        return idCard
    }
}