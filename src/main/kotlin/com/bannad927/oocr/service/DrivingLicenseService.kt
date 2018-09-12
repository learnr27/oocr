package com.bannad927.oocr.service

import com.bannad927.oocr.entity.DrivingLicenseEntity
import com.bannad927.oocr.repository.DrivingLicenseRepository
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
class DrivingLicenseService {

    @Autowired
    lateinit var drivingLicenseRepository: DrivingLicenseRepository

    fun analysisData(res: JSONObject, filePath: String): DrivingLicenseEntity {
        var drivingLicense = DrivingLicenseEntity()
        val resultMap = JSONUtil().readObject<Map<*, *>>(res.toString(), Map::class.java)
        if (null != resultMap.get("error_code")) {
            return drivingLicense
        }
        val wordsResult = resultMap.get("words_result") as Map<*, *>

        for (keyStr in wordsResult.keys) {
            if ("证号" == keyStr) {
                drivingLicense.licenseNo = (wordsResult["证号"] as Map<*, *>)["words"].toString()

            }
            if ("有效期限" == keyStr) {
                drivingLicense.validFor = (wordsResult["有效期限"] as Map<*, *>)["words"].toString()
            }
            if ("准驾车型" == keyStr) {
                drivingLicense.drivingType = (wordsResult["准驾车型"] as Map<*, *>)["words"].toString()
            }
            if ("有效起始日期" == keyStr) {
                drivingLicense.validFrom = (wordsResult["有效起始日期"] as Map<*, *>)["words"].toString()
            }
            if ("住址" == keyStr) {
                drivingLicense.address = (wordsResult["住址"] as Map<*, *>)["words"].toString()
            }
            if ("姓名" == keyStr) {
                drivingLicense.name = (wordsResult["姓名"] as Map<*, *>)["words"].toString()
            }
            if ("国籍" == keyStr) {
                drivingLicense.nationality = (wordsResult["国籍"] as Map<*, *>)["words"].toString()
            }
            if ("出生日期" == keyStr) {
                drivingLicense.birthday = (wordsResult["出生日期"] as Map<*, *>)["words"].toString()
            }
            if ("性别" == keyStr) {
                drivingLicense.sex = (wordsResult["性别"] as Map<*, *>)["words"].toString()
            }
            if ("初次领证日期" == keyStr) {
                drivingLicense.firstIssue = (wordsResult["初次领证日期"] as Map<*, *>)["words"].toString()
            }
        }
        drivingLicense.filePath = filePath
        drivingLicenseRepository.save(drivingLicense)
        return drivingLicense
    }
}