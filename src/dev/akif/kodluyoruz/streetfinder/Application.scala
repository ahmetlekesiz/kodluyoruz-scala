package dev.akif.kodluyoruz.streetfinder

import java.io.File

/**
 * See CSV file at: https://github.com/makiftutuncu/kodluyoruz-scala/blob/main/data/streets.csv
 *
 * Original data: https://github.com/life/il-ilce-mahalle-sokak-cadde-sql
 */
object Application extends CsvLoader with StreetFinder {

  def main(args: Array[String]): Unit = {
    val filePath = "C:\\Users\\Kripton\\Desktop\\Trendyol Data Bootcamp\\kodluyoruz-scala\\data\\streets.csv"
    val file = new File(filePath)
    val streetNames = loadCsv(file)
    val nameSet = Set("ATATÜRK")
    val result = findStreets(streetNames, nameSet)
    print(result)
  }

  override def loadCsv(file: File): List[String] = {
    val streetNames = io.Source.fromFile(file)
      .getLines
      .map(_.split(",")(1))
      .toList
      .drop(1)
    streetNames
  }

  override def findStreets(streets: List[String], names: Set[String]): List[String] = {
    val filteredStreets = streets.filter(e => e.split(" ").filter(p => names.contains(p)).length != 0)
    filteredStreets
  }
}
