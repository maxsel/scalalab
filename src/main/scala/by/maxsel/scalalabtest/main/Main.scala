package by.maxsel.scalalabtest.main

import scala.io.Source
import java.io.PrintWriter
import by.maxsel.scalalabtest.segmentation._

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
object Main {
  val PATH_TO_TRANSACTIONS = "transactions.tsv"
  val PATH_TO_RANGES = "ranges.tsv"
  val PATH_TO_OUTPUT = "output.tsv"

  /**
    * Parses list of strings from transactions.tsv into List[User]
    */
  def getUsersList(transactions: List[String]): List[User] =
    transactions
      .map(_.split("\\t"))
      .map(s => User(s(0).toLong, Address(s(1))))

  /**
    * Parses list of strings from ranges.tsv into List[Segment]
    */
  def getSegmentsList(ranges: List[String]): List[Segment] =
    ranges
      .map(_.split("\\t"))
      .map(e => (
          e(1),
          e(0)
            .split("-")
            .map(Address(_)) match {case Array(a, b, _*) => Range(a, b)}
          ))
      .groupBy(_._1)
      .mapValues(_.map(_._2))
      .map(x => Segment(x._1, x._2))
      .toList

  /**
    * Matches users to lists of networks they belong to
    */
  def segmentUsers(users: List[User], segments: List[Segment]): List[(Long, List[String])] = {
    def append(acc: List[(Long, List[String])], u: User): List[(Long, List[String])] = {
      val networks = segments.filter(_.contains(u)).map(_.name)
      networks match {
        case x :: _ => acc :+ (u.userId, networks)
        case _ => acc
      }
    }
    users.foldLeft(List[(Long, List[String])]())(append)
  }

  /**
    * Writes segmented users to TSV file
    */
  def writeToFile(segmented: List[(Long, List[String])]): Unit = {
    val strings = segmented.flatMap(p => p._2.map(p._1.toString + "\t" + _))
    new PrintWriter(PATH_TO_OUTPUT) { write(strings.mkString("\n")); close() }
  }

  def main(args: Array[String]) {
    val transactions = Source.fromFile(PATH_TO_TRANSACTIONS).getLines.toList
    val users = getUsersList(transactions)

    val ranges = Source.fromFile(PATH_TO_RANGES).getLines.toList
    val segments = getSegmentsList(ranges)

    val usersSegmented = segmentUsers(users, segments)
    writeToFile(usersSegmented)
  }
}
