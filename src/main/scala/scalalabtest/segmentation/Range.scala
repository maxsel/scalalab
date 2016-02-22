package scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Range(minIP: Address, maxIP: Address) {
  override def toString = s"$minIP-$maxIP"
}

object Range {
  def apply(minIP: Address, maxIP: Address) = new Range(minIP, maxIP)

  // test
  def apply(minIP: Int, maxIP: Int): Range = Range(Address(minIP), Address(maxIP))
}