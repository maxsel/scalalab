package scalalabtest.segmentation;

/**
 * Created by Maxim Selyuk on 22.02.16.
 */
class User(val userId: Long, val address: Address) {
  override def toString = s"User($userId, $address)"
}

object User {
  def apply(userId: Long, address: Address) = new User(userId, address)
}