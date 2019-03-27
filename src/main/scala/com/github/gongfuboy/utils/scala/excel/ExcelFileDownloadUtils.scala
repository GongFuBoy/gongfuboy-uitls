package com.github.gongfuboy.utils.scala.excel

import java.io.FileOutputStream
import java.lang.reflect.Field

import com.github.gongfuboy.utils.enums.MergeCellEnum
import com.github.gongfuboy.utils.excel.{Description, MergeCell}
import org.apache.poi.hssf.usermodel._
import org.apache.poi.ss.usermodel.CellStyle

/**
  * Created by ZhouLiMing on 2019/1/31.
  */
object ExcelFileDownloadUtils {

  /**
    * 根据resource获取HSSFWorkbook,这里的List<T>里的T的所有成员变量最好都加上Descripiton的注解,
    * 这是为了每一个成员变量(也就是每一个sheet的列名也就是中文描述，假如不添加的话，那么就会导致，相应的列不进行解析)
    *
    * @param source
    * @param maxSheetNumber
    * @param fileOutputStream
    * @tparam T
    */
  def createHSSFWorkbook[T](source: List[T], maxSheetNumber: Int, fileOutputStream: FileOutputStream) = {
    assert(source != null && source.size > 0, s"source list size is empty")
    val workbook = new HSSFWorkbook()
    val cellStyle = workbook.createCellStyle()
    cellStyle.setAlignment(CellStyle.ALIGN_CENTER)
    val titleNames = source.headOption.getOrElse(throw new NullPointerException).getClass.getDeclaredFields.map(field => {
      field.setAccessible(true)
      val description = field.getAnnotation(classOf[Description])
      if (description != null) Some(description.value()) else None
    }).filter(_.isDefined).map(_.getOrElse(throw new RuntimeException("未知异常")))
    source.sliding(maxSheetNumber, maxSheetNumber).zipWithIndex.foreach({
      case (sheetSourceList, index) => {
        val sheet = workbook.createSheet(s"sheet${index + 1}")
        val firstRow = sheet.createRow(0)
        setFirstRow(firstRow, titleNames.toList, cellStyle)
        setContentRows(sheetSourceList, sheet, cellStyle)
      }
    })
  }

  /**
    * 设置每一个sheet页中第一列
    *
    * @param row
    * @param titles
    * @param style
    */
  private def setFirstRow(row: HSSFRow, titles: List[String], style: HSSFCellStyle) = {
    titles.zipWithIndex.foreach({
      case (titleName, index) => {
        val cell: HSSFCell = row.createCell(index)
        cell.setCellValue(titleName)
        cell.setCellStyle(style)
      }
    })
  }

  /**
    * 设置每一个sheet页的内容
    *
    * @param targetList
    * @param sheet
    * @param style
    * @tparam T
    */
  private def setContentRows[T](targetList: List[T], sheet: HSSFSheet, style: HSSFCellStyle) = {
    targetList.zipWithIndex.foreach({
      case (sourceObject, outIndex) => {
        val row = sheet.createRow(outIndex + 1)
        val fields = sourceObject.getClass.getDeclaredFields.filter(_.getAnnotation(classOf[Description]) != null)
        fields.zipWithIndex.foreach({
          case (field, innerIndex) => {
            val cell = row.createCell(innerIndex)
            cell.setCellStyle(style)
            field.setAccessible(true)
            val value = field.get(sourceObject)
            if (value != null) {
              value match {
                case date: java.util.Date => cell.setCellValue(date)
                case _ => cell.setCellValue(value.toString)
              }
            }
          }
        })
      }
    })

    /**合并单元格*/
    val mergeCellFirstLevel: List[(Option[Field], Any)] = targetList.map(sourceObject => {
      val mergeCellFirstLevelField = sourceObject.getClass.getDeclaredFields.find(x => x.getAnnotation(classOf[MergeCell]) != null
        && x.getAnnotation(classOf[MergeCell]).value().equals(MergeCellEnum.FIRST_LEVEL))
      (mergeCellFirstLevelField, sourceObject)
    })

    if (mergeCellFirstLevel.head._1.isDefined) {
      val mergeCellFirstLevelValues: List[String] = mergeCellFirstLevel.map(x => x._1.get.get(x._2).toString)
      val groupedMergeCell: Map[String, List[String]] = mergeCellFirstLevelValues.groupBy(x => x)
      val firstLevelIndex = targetList.head.getClass.getDeclaredFields.zipWithIndex.find({
        case (sourceField, _) => {
          sourceField.getAnnotation(classOf[MergeCell]) != null && sourceField.getAnnotation(classOf[MergeCell]).value().equals(MergeCellEnum.FIRST_LEVEL)
        }
      }).getOrElse(throw new RuntimeException("未知异常, 无法找到对应的field index"))._2
      groupedMergeCell.foreach({
        case (_, mergeList) => {

        }
      })
    }
  }

}