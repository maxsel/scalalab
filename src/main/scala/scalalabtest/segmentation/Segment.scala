package scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Segment(val name: String, val ranges: List[Range]) {
  def contains(addr: Address): Boolean = ranges.exists(_.contains(addr))

  def contains(user: User): Boolean = ranges.exists(_.contains(user))

  // delete
  override def equals(o: Any) = o match {
    case that: Segment => that.name.equals(this.name)
    case _ => false
  }

  // delete
  override def hashCode = name.hashCode

  // delete
  override def toString = s"'$name'[" + ranges.mkString(",") + "]"
}

object Segment {
  def apply(name: String, ranges: List[Range]) = new Segment(name, ranges)
}