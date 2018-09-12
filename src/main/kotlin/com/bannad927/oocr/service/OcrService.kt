package com.bannad927.oocr.service

import com.baidu.aip.ocr.AipOcr
import com.bannad927.oocr.entity.*
import com.bannad927.oocr.utils.DateUtils
import com.bannad927.oocr.utils.FileUtil
import com.bannad927.oocr.utils.JSONUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import javax.annotation.Resource


/**
 * Created by: 程彬彬
 * Date: 2018/8/24
 * Describe:
 */
@Service

class OcrService {

    private val log = LoggerFactory.getLogger(OcrService::class.java)

    val APP_ID = "APP_ID"
    val API_KEY = "API_KEY"
    val SECRET_KEY = "SECRET_KEY"

    @Value("\${receivePath}")
    var receivePath: String? = null

    var dateUtils = DateUtils()

    var fileUtil = FileUtil()
    var client = AipOcr(APP_ID, API_KEY, SECRET_KEY)

    @Resource
    lateinit var vehicleLicenseService: VehicleLicenseService

    @Resource
    lateinit var basicAccurateGeneralService: BasicAccurateGeneralService

    @Resource
    lateinit var idCardService: IdCardService

    @Resource
    lateinit var bankCardService: BankCardService

    @Resource
    lateinit var drivingLicenseService: DrivingLicenseService

    @Resource
    lateinit var plateLicenseService: PlateLicenseService

    @Resource
    lateinit var businessLicenseService: BusinessLicenseService

    @Resource
    lateinit var receiptService:ReceiptService

    fun receipt(path: String?): String {
        val options = HashMap<String, String>()
        options.put("recognize_granularity", "big")
        options.put("probability", "true")
        options.put("accuracy", "normal")
        options.put("detect_direction", "false")

        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "Receipt/" + dateTime + "/" + path
        var outFile = receivePath + pf

        val res = client.receipt(inFile, options)
        var resultData = receiptService.analysisData(res)
        if ("" != resultData) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }

        return resultData
    }


    fun businessLicense(path: String?): BusinessLicenseEntity {
        val options = HashMap<String, String>()
        options.put("multi_detect", "false")
        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "BusinessLicense/" + dateTime + "/" + path
        var outFile = receivePath + pf
        val res = client.businessLicense(inFile, options)
        var businessLicense = businessLicenseService.analysisData(res, pf)

        if ("" != businessLicense.companyName || "" != businessLicense.socialCreditCode) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }
        return businessLicense
    }

    fun plateLicense(path: String?): PlateLicenseEntity {
        val options = HashMap<String, String>()
        options.put("multi_detect", "false")
        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "PlateLicense/" + dateTime + "/" + path
        var outFile = receivePath + pf
        val res = client.plateLicense(inFile, options)
        var plateLicense = plateLicenseService.analysisData(res, pf)

        if ("" != plateLicense.number || "" != plateLicense.color) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }
        return plateLicense
    }

    fun drivingLicense(path: String?): DrivingLicenseEntity {
        val options = HashMap<String, String>()
        options.put("unified_valid_period", "true")
        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "DrivingLicense/" + dateTime + "/" + path
        var outFile = receivePath + pf
        val res = client.drivingLicense(inFile, options)
        var drivingLicense = drivingLicenseService.analysisData(res, pf)

        if ("" != drivingLicense.name || "" != drivingLicense.licenseNo) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }
        return drivingLicense
    }

    fun bankcard(path: String?): BankCardEntity {
        val options = HashMap<String, String>()
        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "BankCard/" + dateTime + "/" + path
        var outFile = receivePath + pf
        val res = client.bankcard(inFile, options)
        var bankCard = bankCardService.analysisData(res, pf)

        if ("" != bankCard.bankCardNumber || "" != bankCard.bankName) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }
        return bankCard
    }

    fun idCard(path: String?): IdCardEntity {
        val options = HashMap<String, String>()
        options.put("detect_direction", "true")
        options.put("detect_risk", "false")

        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "IdCard/" + dateTime + "/" + path
        var outFile = receivePath + pf
        val res = client.idcard(inFile, "front", options)
        var idCard = idCardService.analysisData(res, pf)

        if ("" != idCard.cardNo || "" != idCard.address) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }
        return idCard
    }

    fun basicAccurateGeneral(path: String?): String {
        val options = HashMap<String, String>()
        options.put("recognize_granularity", "big")
        options.put("language_type", "CHN_ENG")
        options.put("detect_direction", "true")
        options.put("detect_language", "true")
        options.put("vertexes_location", "true")
        options.put("probability", "true")
        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "BasicAccurateGeneral/" + dateTime + "/" + path
        var outFile = receivePath + pf

        val res = client.basicAccurateGeneral(inFile, options)
        var resultData = basicAccurateGeneralService.analysisData(res)
        if ("" != resultData) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }

        return resultData
    }

    fun vehicleLicense(path: String?): VehicleLicenseEntity {
        val options = HashMap<String, String>()
        options.put("detect_direction", "true")

        var inFile = receivePath + path
        var dateTime = dateUtils.dateToStr(Date())
        var pf = "VehicleLicense/" + dateTime + "/" + path
        var outFile = receivePath + pf
        val res = client.vehicleLicense(inFile, options)
        var vehicleLicenseEntity = vehicleLicenseService.analysisData(res, pf)

        if ("" != vehicleLicenseEntity.plateNo || "" != vehicleLicenseEntity.brandModel) {
            fileUtil.mkFile(outFile)
            fileUtil.fileCopy(File(inFile), File(outFile))
        }

        log.info("result:{}", JSONUtil().toJSONString(vehicleLicenseEntity))
        return vehicleLicenseEntity
    }

}