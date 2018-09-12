package com.bannad927.oocr.repository

import com.bannad927.oocr.entity.IdCardEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by: 程彬彬
 * Date: 2018/8/31
 * Describe:
 */
interface IdCardRepository : JpaRepository<IdCardEntity, Integer> {

}