package scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Range(minIP: Address, maxIP: Address) {
  def contains(addr: Address): Boolean = minIP <= addr && addr <= maxIP

  def contains(user: User): Boolean = minIP <= user.address && user.address <= maxIP

  // delete
  override def toString = s"$minIP-$maxIP"
}

object Range {
  def apply(minIP: Address, maxIP: Address) = new Range(minIP, maxIP)

  // delete
  def apply(minIP: Long, maxIP: Long): Range = Range(Address(minIP), Address(maxIP))
}