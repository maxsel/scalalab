package by.maxsel.scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Segment(val name: String, val ranges: List[Range]) {
  def contains(addr: Address): Boolean = ranges.exists(_.contains(addr))

  def contains(user: User): Boolean = ranges.exists(_.contains(user))
}

object Segment {
  def apply(name: String, ranges: List[Range]) = new Segment(name, ranges)
}