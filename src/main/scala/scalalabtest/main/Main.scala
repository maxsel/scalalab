package scalalabtest.main

import java.io.PrintWriter

import scala.io.Source
import scalalabtest.segmentation._

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
object Main {
  val DEFAULT_PATH_TO_TRANSACTIONS = "src/main/resources/transactions_big.tsv"
  val DEFAULT_PATH_TO_RANGES = "src/main/resources/ranges_big.tsv"
  val OUTPUT_FILE_NAME = "output.tsv"

  /**
    * Parses list of strings from transactions.tsv into List[User]
    */
  def getUsersList(transactions: List[String]): List[User] =
    transactions
      .map(str => str.split("\\t"))
      .map(e => User(e(0).toLong, Address(e(1))))

  /**
    * Parses list of strings from ranges.tsv into List[Segment]
    */
  def getSegmentsList(ranges: List[String]): List[Segment] =
    ranges
      .map(str => str.split("\\t"))
      .map(e => (
          e(1),
          e(0)
            .split("-")
            .map(s => Address(s)) match {case Array(a,b,_*) => Range(a,b)}
          ))
      .groupBy(_._1)
      .mapValues(l => l.map(p => p._2))
      .map(x => Segment(x._1, x._2))
      .toList

  /**
    * Matches users to list of networks they belong to
    */
  def segmentUsers(users: List[User], segments: List[Segment]): List[(Long, List[String])] =
    users.map(u => (u.userId, segments.filter(_.contains(u)).map(_.name)))

  /**
    * Writes segmented users to TSV file
    */
  def writeToFile(segmented: List[(Long, List[String])]): Unit = {
    val strings = segmented.map(p => p._1.toString + "\t" + p._2.mkString)
    new PrintWriter(OUTPUT_FILE_NAME) { write(strings.mkString("\n")); close() }
  }

  def main(args: Array[String]) {
    def resolveInputFiles: (String, String) = args.length match {
      case 0 | 1 => (DEFAULT_PATH_TO_TRANSACTIONS, DEFAULT_PATH_TO_RANGES)
      case _ => (args(0), args(1))
    }

    val (pathToTransactions, pathToRanges) = resolveInputFiles

    val transactions = Source.fromFile(pathToTransactions).getLines.toList
    val users = getUsersList(transactions)

    val ranges = Source.fromFile(pathToRanges).getLines.toList
    val segments = getSegmentsList(ranges)

    val segmented = segmentUsers(users, segments)
    writeToFile(segmented)
  }
}
