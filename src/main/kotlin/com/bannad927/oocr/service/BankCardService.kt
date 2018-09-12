package com.bannad927.oocr.service

import com.bannad927.oocr.entity.BankCardEntity
import com.bannad927.oocr.entity.IdCardEntity
import com.bannad927.oocr.repository.BankCardRepository
import com.bannad927.oocr.utils.JSONUtil
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by: 程彬彬
 * Date: 2018/9/3
 * Describe:
 */
@Service
class BankCardService {

    @Autowired
    lateinit var bankCardRepository: BankCardRepository

    fun analysisData(res: JSONObject, filePath: String): BankCardEntity {
        var bankCard = BankCardEntity()
        val resultMap = JSONUtil().readObject<Map<*, *>>(res.toString(), Map::class.java)
        if (null != resultMap.get("error_code")) {
            return bankCard
        }
        val wordsResult = resultMap.get("result") as Map<*, *>

        bankCard.bankCardNumber = (wordsResult["bank_card_number"]).toString()
        bankCard.bankName = (wordsResult["bank_name"]).toString()
        bankCard.bankCardType = (wordsResult["bank_card_type"]).toString()

        bankCard.filePath = filePath
        bankCardRepository.save(bankCard)
        return bankCard
    }
}