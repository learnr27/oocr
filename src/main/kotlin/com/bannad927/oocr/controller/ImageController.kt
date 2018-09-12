package com.bannad927.oocr.controller

import com.bannad927.oocr.common.Result
import com.bannad927.oocr.utils.DateUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.File

import java.io.IOException
import java.util.HashMap

/**
 * Created by: 程彬彬
 * Date: 2018/8/24
 * Describe:
 */
@Controller
@RequestMapping("/image")
class ImageController {
    private val logger = LoggerFactory.getLogger(ImageController::class.java)

    @Value("\${receivePath}")
    var receivePath: String? = null

    @RequestMapping(value = "/upload")
    @ResponseBody
    fun upload(@RequestParam("file") file: MultipartFile): Result {
        val result = Result()
        result.code="0"

        if (file.isEmpty) {
            result.code="-1"
            result.msg="文件为空"
            return result
        }
        // 获取文件名
        var fileName: String = file?.originalFilename!!
        logger.info("fileName：" + fileName)
        // 获取文件的后缀名
        val suffixName = fileName.substring(fileName.lastIndexOf("."))
        logger.info("suffixName：" + suffixName)
        // 文件上传后的路径

        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = DateUtils().getNowTime("yyyyMMddHHmmss") + suffixName
        val imagePath = receivePath + fileName
        val dest = File(imagePath)
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs()
        }
        try {
            file.transferTo(dest)
            result.msg="success"
            val data = HashMap<String, Any>()
            val imageUrl = fileName
            data.put("imageUrl", imageUrl)
            result.content=data
            return result
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        result.code="-1"
        result.msg="failed"
        return result
    }
}