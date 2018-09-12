package com.bannad927.oocr.controller

import com.alibaba.druid.util.StringUtils
import com.bannad927.oocr.common.Result
import com.bannad927.oocr.service.OcrService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.io.File

/**
 * Created by: 程彬彬
 * Date: 2018/8/24
 * Describe:
 */
@Controller
class IndexController  @Autowired constructor(
        private val ocrService: OcrService
){

    private val log = LoggerFactory.getLogger(IndexController::class.java)

    @Value("\${receivePath}")
    var receivePath: String? = null

    @RequestMapping("/index")
    fun index(): String {
        log.info("index:{}", System.nanoTime())
        return "index"
    }

    @RequestMapping("/oocr")
    fun upload(model:Model,@RequestParam("type") type: String, @RequestParam("fileName") fileName: String): String {
        var result = Result()
        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(fileName)) {
            model.addAttribute("result","文件类别或文件不能为空")
            return "error"
        }

        var file: File? = File(receivePath+fileName)
        if(false==(file?.exists())){
            model.addAttribute("result","找不到文件")
            return "error"
        }


        when (type) {
            "0"->{
            var resultData=ocrService.basicAccurateGeneral(fileName)
                if ("".equals(result)){
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",resultData)
                return "basic_accurate_general.html"
            }
            "2"->{
                var resultData=ocrService.idCard(fileName)
                if ("" == resultData.cardNo || "" == resultData.address) {
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",resultData)
                return "id_card.html"
            }
            "3"->{
                var resultData=ocrService.bankcard(fileName)
                if ("" == resultData.bankCardNumber || "" == resultData.bankName) {
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",resultData)
                return "bank_card.html"
            }
            "4"->{
                var resultData=ocrService.drivingLicense(fileName)
                if ("" == resultData.name || "" == resultData.licenseNo) {
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",resultData)
                return "driving_license.html"

            }
            "5"->  {
                var vehicleLicense =  ocrService.vehicleLicense(fileName)
                if (""==vehicleLicense.plateNo||""==vehicleLicense.brandModel){
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",vehicleLicense)
                return "vehicle_license.html"
            }
            "6"->{
                var resultData=ocrService.plateLicense(fileName)
                if ("" == resultData.number || "" == resultData.color) {
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",resultData)
                return "plate_license.html"
            }
            "7"->{
                var resultData=ocrService.businessLicense(fileName)
                if ("" == resultData.companyName || "" == resultData.socialCreditCode) {
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",resultData)
                return "business_license.html"
            }
            "8"->{
                var resultData=ocrService.receipt(fileName)
                if ("".equals(result)){
                    model.addAttribute("result","识别失败")
                    return "error"
                }
                model.addAttribute("result",resultData)
                return "receipt.html"
            }
        }
        return "error"

    }
}