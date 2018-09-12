package com.bannad927.oocr.utils

import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by: 程彬彬
 * Date: 2018/8/27
 * Describe:
 */
class FileUtil {

    protected var logger = LoggerFactory.getLogger(FileUtil::class.java!!)

    @Throws(IOException::class)
    fun fileCopy(inFile: File, out: File) {

        Thread(object : Runnable {
            override fun run() {
                val inChannel = FileInputStream(inFile).channel
                val outChannel = FileOutputStream(out).channel
                try {
                    val maxCount = 64 * 1024 * 1024 - 32 * 1024
                    val size = inChannel!!.size()
                    var position: Long = 0
                    while (position < size) {
                        position += inChannel
                                .transferTo(position, maxCount.toLong(), outChannel)
                    }
                } finally {
                    inChannel?.close()
                    outChannel?.close()
                    deleteFile(inFile)
                }
            }
        }).start()
    }


    fun delete(fileName: String?): Boolean {
        if (fileName == null || fileName.trim { it <= ' ' }.length == 0) return false
        val file = File(fileName)
        return delete(file!!)
    }

    fun delete(file: File?): Boolean {
        if (file == null) return false
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile) { // 判断是否是文件
                file.delete() // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory) { // 否则如果它是一个目录
                val files = file.listFiles() // 声明目录下所有的文件 files[];
                for (i in files!!.indices) { // 遍历目录下所有的文件
                    deleteFile(files[i]) // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete()
            return true
        } else {
            logger.error("FileUtil.deleteFileOrDirectory.FileNotFoundException :找不到"
                    + file.path + "文件！")
            return false
        }
    }

    fun mkFile(filePath: String): Boolean {
        try {
            var file: File? = File(filePath)
            val parentFile = file!!.parentFile
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs()// 创建父文件夹 如果已有父文件夹则创建失败
            }
            val flag = file.createNewFile()
            file = null
            if (flag)
                logger.debug("创建文件完成")
            return flag
        } catch (e: IOException) {
            logger.error(e.toString(), e)
        }

        return false
    }

    fun deleteFile(file: File?): Boolean {
        if (file == null) return false
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile) { // 判断是否是文件
                file.delete() // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory) { // 否则如果它是一个目录
                return false
            }
            file.delete()
            return true
        } else {
            logger.error("FileUtil.deleteFile.FileNotFoundException :找不到"
                    + file.path + "文件！")
            return false
        }
    }
}