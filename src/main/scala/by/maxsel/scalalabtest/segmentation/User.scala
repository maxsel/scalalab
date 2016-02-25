package by.maxsel.scalalabtest.segmentation

/**
 * Created by Maxim Selyuk on 22.02.16.
 */
class User(val userId: Long, val address: Address) {

}

object User {
  def apply(userId: Long, address: Address) = new User(userId, address)
}