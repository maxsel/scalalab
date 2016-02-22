package scalalabtest.main

import scala.io.Source
import scalalabtest.segmentation._

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
object Main {
  val DEFAULT_PATH_TO_TRANSACTIONS = "src/main/resources/transactions.tsv"
  val DEFAULT_PATH_TO_RANGES = "src/main/resources/ranges.tsv"

  def main(args: Array[String]) {
    def resolveInputFiles: (String, String) = args.length match {
      case 0 | 1 => (DEFAULT_PATH_TO_TRANSACTIONS, DEFAULT_PATH_TO_RANGES)
      case _ => (args(0), args(1))
    }

    val (pathToTransactions, pathToRanges) = resolveInputFiles

    println("---TRANSACTIONS---")
    val transactions = Source.fromFile(pathToTransactions).getLines.toList
    val users: List[User] =
      transactions
        .map(str => str.split("\\t"))
        .map(e => User(e(0).toLong, Address(e(1))))
    users.foreach(println)

    println("---RANGES---")
    val ranges = Source.fromFile(pathToRanges).getLines.toList
    val segments: Set[Segment] =
      ranges
        .map(str => str.split("\\t"))
        .map(e => Segment(e(1), List(Range(Address(e(0).split("-")(0)), Address(e(0).split("-")(1))))))
        .toSet
    segments.foreach(println)


  }
}
