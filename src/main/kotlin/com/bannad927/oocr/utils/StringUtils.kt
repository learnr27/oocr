package com.bannad927.oocr.utils

import org.slf4j.LoggerFactory
import org.springframework.util.NumberUtils
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.regex.Pattern

/**
 * Created by: 程彬彬
 * Date: 2018/8/27
 * Describe:
 */
class StringUtils {

    private val LOGGER = LoggerFactory.getLogger(StringUtils::class.java)
    private val TRUE = "true"
    private val CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]")
    private val LPN_COLOR_PATTERN = Pattern.compile("^[蓝黑黄]")

    private val PATTERN_LPN_STANDED = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[a-z]{1}[abcdefghjklmnpqrstuvwxyzz0-9]{4}[abcdefghjklmnpqrstuvwxyzz0-9学港澳警]{1}$", Pattern.CASE_INSENSITIVE)//七位普通车牌
    private val GREEN_LPN_STANDED = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[a-z]{1}[abcdefghjklmnpqrstuvwxyzz00-9]{6}$", Pattern.CASE_INSENSITIVE)//8位新能源车牌

    /**
     * 正则表达式：验证手机号
     */
    private val REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"

    fun hasText(record: Any): Boolean {
        return notNull(record) && org.springframework.util.StringUtils.hasText(record.toString())
    }

    fun isTrue(record: Any): Boolean {
        return notNull(record) && TRUE == record.toString()
    }

    fun notNull(record: Any?): Boolean {
        return record != null
    }

    fun formatMoney(money: String): String {
        if (hasText(money)) {
            return "0.00"
        } else if (money.indexOf(".") == -1) {
            return money + ".00"
        } else {
            val temp = money.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (temp[1].length == 2) {
                return money
            } else {
                return money + "0"
            }
        }
    }

    fun urlEncode(param: String): String {
        var rtn: String
        try {
            rtn = URLEncoder.encode(param, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            rtn = param
        }

        return rtn
    }

    /**
     * 是否标准车牌号

     * @param lpn
     * *
     * @return
     */
    fun isStandedLicencePlateNumber(lpn: String): Boolean {
        return hasText(lpn) && (PATTERN_LPN_STANDED.matcher(lpn).find() || GREEN_LPN_STANDED.matcher(lpn).find())
    }

    /**
     * 验证手机号

     * @param phoneNumber
     * *
     * @return
     */
    fun isMobilePhoneNumber(phoneNumber: String): Boolean {
        return hasText(phoneNumber) && Pattern.matches(REGEX_MOBILE, phoneNumber)
    }

    /**
     * 将十进制传化为16进制的字符串
     * <pre>
     * 空车票打印的小票号是按两位格式由十进制转化为十六进制，如转化后的十六进制不足2位，在前面补0
    </pre> *

     * @param receiptNo
     * *
     * @return
     */
    fun parseReceiptString(receiptNo: String): String {
        val rtn = StringBuffer()
        var i = 0
        while (i < receiptNo.length) {
            try {
                val hexString = Integer.toHexString(NumberUtils.parseNumber(receiptNo.substring(i, i + 2), Int::class.java))
                rtn.append(if (hexString.length == 1) "0" + hexString else hexString)
            } catch (ex: Exception) {
                LOGGER.error("小票号转换出现异常:{}", ex)
            }

            i = i + 2
        }
        return rtn.toString().toUpperCase()
    }

    fun isContainsChinese(str: String): Boolean {
        val matcher = CHINESE_PATTERN.matcher(str)
        var flg = false
        if (matcher.find()) {
            flg = true
        }
        return flg
    }

    /**
     * 删除车牌的首位汉字

     * @param lpn
     * *
     * @return
     */
    fun deleteLpnFirstChinese(lpn: String): String {
        if (hasText(lpn) && CHINESE_PATTERN.matcher(lpn.substring(0, 1)).find()) {
            return lpn.substring(1)
        } else {
            return lpn
        }
    }

    fun deleteLpnColorChinese(lpn: String): String {
        if (hasText(lpn) && LPN_COLOR_PATTERN.matcher(lpn.substring(0, 1)).find()) {
            return lpn.substring(1)
        } else {
            return lpn
        }
    }

    fun isNumeric(str: String): Boolean {
        val pattern = Pattern.compile("[0-9]*")
        val isNum = pattern.matcher(str)
        return isNum.matches()
    }

    /**
     * 替换URL字符串指定参数的值

     * @param url                要替换的URL字符串
     * *
     * @param paramName          要替换的参数名
     * *
     * @param paramValue         要替换的参数值
     * *
     * @param isAppendIfNoExists 如果url中不存在要替换的参数名是否进行追加
     * *
     * @return
     */
    fun replaceUrlParamter(url: String, paramName: String, paramValue: String, isAppendIfNoExists: Boolean): String {
        var repaceUrl = ""
        val paramNameReg = Pattern.compile(paramName + "=[^&]*")
        if (!paramNameReg.matcher(url).find() && isAppendIfNoExists) {
            repaceUrl = StringBuffer(url).append(if (url.indexOf("?") > 0) "&" else "?").append(paramName).append("=").append(paramValue).toString()
        } else if (hasText(url) && hasText(paramValue)) {
            repaceUrl = url.replace("($paramName=[^&]*)".toRegex(), paramName + "=" + paramValue)
        } else {
            repaceUrl = url
        }
        return repaceUrl

    }
}