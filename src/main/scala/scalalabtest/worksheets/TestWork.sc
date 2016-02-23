import scalalabtest.segmentation._
object TestWork {
  val tr = List(
    "1	192.168.1.1", "11	192.168.1.2", "2	192.168.2.2", "3	192.168.3.3")
  val ran = List(
    "192.168.1.0-192.168.1.255	Network1", "192.168.2.0-192.168.2.255	Network2",
    "192.168.3.0-192.168.3.255	Network3", "192.168.4.0-192.168.4.255	Network3")

}