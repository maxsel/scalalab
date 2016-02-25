package by.maxsel.scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Address(val value: Long) {
  def <=(that: Address) = this.value <= that.value

  def >=(that: Address) = this.value >= that.value
}

object Address {
  val POWERS = List(16777216, 65536, 256, 1)

  def apply(value: Long) = new Address(value)

  def apply(strVal: String) = fromString(strVal)

  /**
    *  Translates IP address from dotted-decimal address to decimal format
    */
  def fromString(str: String): Address =
    Address(str.split("\\.").map(_.toLong).zip(POWERS).map(p => p._1*p._2).sum)
}