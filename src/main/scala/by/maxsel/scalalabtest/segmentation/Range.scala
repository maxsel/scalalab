package by.maxsel.scalalabtest.segmentation

/**
  * Created by Maxim Selyuk on 22.02.16.
  */
class Range(val minIP: Address, val maxIP: Address) {
  def contains(addr: Address): Boolean = minIP <= addr && addr <= maxIP

  def contains(user: User): Boolean = minIP <= user.address && user.address <= maxIP
}

object Range {
  def apply(minIP: Address, maxIP: Address) = new Range(minIP, maxIP)
}