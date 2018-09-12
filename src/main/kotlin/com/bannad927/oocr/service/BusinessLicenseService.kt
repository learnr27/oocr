package com.bannad927.oocr.service

import com.bannad927.oocr.entity.BusinessLicenseEntity
import com.bannad927.oocr.repository.BusinessLicenseRepository
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
class BusinessLicenseService {

    @Autowired
    lateinit var businessLicenseRepository: BusinessLicenseRepository

    fun analysisData(res: JSONObject, filePath: String): BusinessLicenseEntity {
        var businessLicense = BusinessLicenseEntity()
        val resultMap = JSONUtil().readObject<Map<*, *>>(res.toString(), Map::class.java)
        if (null != resultMap.get("error_code")) {
            return businessLicense
        }
        val wordsResult = resultMap.get("words_result") as Map<*, *>

        for (keyStr in wordsResult.keys) {
            if ("单位名称" == keyStr) {
                businessLicense.companyName = (wordsResult["单位名称"] as Map<*, *>)["words"].toString()
            }
            if ("法人" == keyStr) {
                businessLicense.legalPerson = (wordsResult["法人"] as Map<*, *>)["words"].toString()
            }
            if ("地址" == keyStr) {
                businessLicense.address = (wordsResult["地址"] as Map<*, *>)["words"].toString()
            }
            if ("有效期" == keyStr) {
                businessLicense.validPeriod = (wordsResult["有效期"] as Map<*, *>)["words"].toString()
            }
            if ("证件编号" == keyStr) {
                businessLicense.licenseNo = (wordsResult["证件编号"] as Map<*, *>)["words"].toString()
            }
            if ("社会信用代码" == keyStr) {
                businessLicense.socialCreditCode = (wordsResult["社会信用代码"] as Map<*, *>)["words"].toString()
            }
        }
        businessLicense.filePath = filePath
        businessLicenseRepository.save(businessLicense)
        return businessLicense
    }
}