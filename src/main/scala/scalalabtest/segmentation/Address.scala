package scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Address(val value: Long) {
  override def toString = s"$value"
}

object Address {
  val POWERS = List(16777216, 65536, 256, 1)

  def apply(value: Long) = new Address(value)

  def apply(strVal: String) = fromString(strVal)

  /** Translates IP address from dotted-decimal address to decimal format
    * @param str IP address in dotted-decimal representation
    * @return an object of class Address
    */
  def fromString(str: String): Address =
    Address(str.split("\\.").map(_.toLong).zip(POWERS).map(p => p._1*p._2).sum)
}