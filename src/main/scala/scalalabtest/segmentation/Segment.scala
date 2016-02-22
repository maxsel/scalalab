package scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Segment(val name: String, val ranges: List[Range]) {
  override def equals(o: Any) = o match {
    case that: Segment => that.name.equals(this.name)
    case _ => false
  }

  override def hashCode = name.hashCode

  override def toString = s"$name[" + ranges.mkString(",") + "]"
}

object Segment {
  def apply(name: String, ranges: List[Range]) = new Segment(name, ranges)
}