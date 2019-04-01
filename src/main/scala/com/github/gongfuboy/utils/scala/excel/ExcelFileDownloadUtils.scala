package com.github.gongfuboy.utils.scala.excel

import java.io.FileOutputStream
import java.lang.reflect.Field

import com.github.gongfuboy.utils.enums.MergeCellEnum
import com.github.gongfuboy.utils.excel.{Description, MergeCell}
import org.apache.commons.io.IOUtils
import org.apache.poi.hssf.usermodel._
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.util.CellRangeAddress

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
    workbook.write(fileOutputStream)
    IOUtils.closeQuietly(fileOutputStream)
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

  }

  /**
    * 合并所有级别的单元格
    * @param targetList
    * @param sheet
    * @tparam T
    */
  private def mergeAllLevel[T](targetList: List[T], sheet: HSSFSheet) = {
    val allLevelFieldTuples: List[(Option[Field], Option[Field], Option[Field], Any)] = targetList.map(sourceObject => {
      val mergeCellFirstLevelField = sourceObject.getClass.getDeclaredFields.find(x => x.getAnnotation(classOf[MergeCell]) != null
        && x.getAnnotation(classOf[MergeCell]).value().equals(MergeCellEnum.FIRST_LEVEL))
      val mergeCellSecondLevelField = sourceObject.getClass.getDeclaredFields.find(x => x.getAnnotation(classOf[MergeCell]) != null
        && x.getAnnotation(classOf[MergeCell]).value().equals(MergeCellEnum.SECOND_LEVEL))
      val mergeCellThirdLevelField = sourceObject.getClass.getDeclaredFields.find(x => x.getAnnotation(classOf[MergeCell]) != null
        && x.getAnnotation(classOf[MergeCell]).value().equals(MergeCellEnum.THIRD_LEVEL))
      (mergeCellFirstLevelField, mergeCellSecondLevelField, mergeCellThirdLevelField, sourceObject)
    })
    /**合并第一级别单元格*/
    val firstLevelFieldOption = allLevelFieldTuples.head._1
    if (firstLevelFieldOption.isDefined) {
      /**确定FirstLevel在Excel中的index*/
      val (_, firstLevelExcelIndex) = targetList.head.getClass.getDeclaredFields.zipWithIndex.find(x => {
        val mergeCell = x._1.getAnnotation(classOf[MergeCell])
        mergeCell != null && mergeCell.value().equals(MergeCellEnum.FIRST_LEVEL)
      }).getOrElse(throw new NullPointerException("can not find first level field index"))
      val firstLevelFieldTuples: List[(Field, Any, Int)] = allLevelFieldTuples.zipWithIndex.map({
        case ((firstLevelField, _, _, sourceObject), index) => (firstLevelField.get, sourceObject, index)
      })
      mergeCell(sheet, firstLevelExcelIndex, firstLevelFieldTuples)
    }
    /**合并第二级别单元格*/
    val secondLevelFieldOption = allLevelFieldTuples.head._2
    if (secondLevelFieldOption.isDefined) {
      /**确定SecondLevel在Excel中的index*/
      val (_, secondLevelExcelIndex) = targetList.head.getClass.getDeclaredFields.zipWithIndex.find(x => {
        val mergeCell = x._1.getAnnotation(classOf[MergeCell])
        mergeCell != null && mergeCell.value().equals(MergeCellEnum.SECOND_LEVEL)
      }).getOrElse(throw new NullPointerException("can not find first level field index"))
      val secondLevelFieldTuples: List[(Field, Any, Int)] = allLevelFieldTuples.zipWithIndex.map({
        case ((_, secondLevelField, _, sourceObject), index) => (secondLevelField.get, sourceObject, index)
      })
      mergeCell(sheet, secondLevelExcelIndex, secondLevelFieldTuples)
    }
    /**合并第三级别单元格*/
    val thirdLevelFieldOption = allLevelFieldTuples.head._2
    if (thirdLevelFieldOption.isDefined) {
      /**确定ThirdLevel在Excel中的index*/
      val (_, thirdLevelExcelIndex) = targetList.head.getClass.getDeclaredFields.zipWithIndex.find(x => {
        val mergeCell = x._1.getAnnotation(classOf[MergeCell])
        mergeCell != null && mergeCell.value().equals(MergeCellEnum.THIRD_LEVEL)
      }).getOrElse(throw new NullPointerException("can not find first level field index"))
      val thirdLevelFieldTuples: List[(Field, Any, Int)] = allLevelFieldTuples.zipWithIndex.map({
        case ((_, _, thirdLevelField, sourceObject), index) => (thirdLevelField.get, sourceObject, index)
      })
      mergeCell(sheet, thirdLevelExcelIndex, thirdLevelFieldTuples)
    }
  }

  private def mergeCell(sheet: HSSFSheet, levelExcelIndex: Int, levelFieldIndexTuples: List[(Field, Any, Int)]) = {
    var levelValue: Object = null
    var levelCount = 0
    levelFieldIndexTuples.foreach({
      case (firstLevelField, sourceObject, index) => {
        if (index == 0) {
          firstLevelField.setAccessible(true)
          levelValue = firstLevelField.get(sourceObject)
        }
        if (levelValue != null && levelValue.equals({
          firstLevelField.setAccessible(true)
          firstLevelField.get(sourceObject)
        }) && index != 0) levelCount = levelCount + 1
        else {
          val cellRangeAddress = new CellRangeAddress(index - levelCount, index , levelExcelIndex, levelExcelIndex)
          sheet.addMergedRegion(cellRangeAddress)
          levelCount = 0
          levelValue = firstLevelField.get(sourceObject)
        }
      }
    })
  }

  /**
    * 合并第一个级别的单元格
    * @param targetList
    * @param sheet
    * @tparam T
    */
  private def mergeFirstLevel[T](targetList: List[T], sheet: HSSFSheet) = {
    /**合并单元格*/
    val mergeCellFirstLevel: List[(Option[Field], Any)] = targetList.map(sourceObject => {
      val mergeCellFirstLevelField = sourceObject.getClass.getDeclaredFields.find(x => x.getAnnotation(classOf[MergeCell]) != null
        && x.getAnnotation(classOf[MergeCell]).value().equals(MergeCellEnum.FIRST_LEVEL))
      (mergeCellFirstLevelField, sourceObject)
    })

    if (mergeCellFirstLevel.head._1.isDefined) {
      val mergeCellFirstLevelValues: List[String] = mergeCellFirstLevel.map(x => {
        x._1.get.setAccessible(true)
        x._1.get.get(x._2).toString
      })
      val groupedMergeCell: List[(String, List[String])] = mergeCellFirstLevelValues.groupBy(x => x).toList.sortBy(_._1)
      val firstLevelIndex = targetList.head.getClass.getDeclaredFields.zipWithIndex.find({
        case (sourceField, _) => {
          sourceField.getAnnotation(classOf[MergeCell]) != null && sourceField.getAnnotation(classOf[MergeCell]).value().equals(MergeCellEnum.FIRST_LEVEL)
        }
      }).getOrElse(throw new RuntimeException("未知异常, 无法找到对应的field index"))._2
      val iterator: List[List[List[String]]] = groupedMergeCell.map(_._2).inits.filter(!_.isEmpty).toList.reverse
      iterator.map(x => x.map(_.size).sum).sliding(2).zipWithIndex.foreach({
        case (skipList, index) => {
          if (index == 0) {
            val cellRangeAddress = new CellRangeAddress(1, skipList(0), firstLevelIndex, firstLevelIndex)
            sheet.addMergedRegion(cellRangeAddress)
            val secondCellRangeAddress = new CellRangeAddress(skipList(0) + 1, skipList(1), firstLevelIndex, firstLevelIndex)
            sheet.addMergedRegion(secondCellRangeAddress)
          } else {
            val cellRangeAddress = new CellRangeAddress(skipList(0) + 1, skipList(1), firstLevelIndex, firstLevelIndex)
            sheet.addMergedRegion(cellRangeAddress)
          }
        }
      })
    }
  }

}