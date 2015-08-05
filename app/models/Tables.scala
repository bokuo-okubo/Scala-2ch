package models

import org.joda.time.DateTime
import slick.driver.H2Driver.api._
import slick.lifted.{ProvenShape, ForeignKeyQuery}

import com.github.tototoshi.slick.H2JodaSupport._

/**
 * Created by bko on 8/5/15.
 */
trait Tables {

}


// A Suppliers table with 6 columns: id, name, street, city, state, zip
class Suppliers(tag: Tag)
  extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("SUP_ID", O.PrimaryKey)
  def name: Rep[String] = column[String]("SUP_NAME")
  def street: Rep[String] = column[String]("STREET")
  def city: Rep[String] = column[String]("CITY")
  def state: Rep[String] = column[String]("STATE")
  def zip: Rep[String] = column[String]("ZIP")

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, String, String, String, String)] =
    (id, name, street, city, state, zip)
}

// A Coffees table with 5 columns: name, supplier id, price, sales, total
class Coffees(tag: Tag)
  extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {

  def name: Rep[String] = column[String]("COF_NAME", O.PrimaryKey)
  def supID: Rep[Int] = column[Int]("SUP_ID")
  def price: Rep[Double] = column[Double]("PRICE")
  def sales: Rep[Int] = column[Int]("SALES")
  def total: Rep[Int] = column[Int]("TOTAL")

  def * : ProvenShape[(String, Int, Double, Int, Int)] =
    (name, supID, price, sales, total)

  // A reified foreign key relation that can be navigated to create a join
  def supplier: ForeignKeyQuery[Suppliers, (Int, String, String, String, String, String)] =
    foreignKey("SUP_FK", supID, TableQuery[Suppliers])(_.id)
}


class Users(tag: Tag)
  extends Table[(Int, String, String, String, DateTime)](tag, "USERS") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("USER_ID", O.PrimaryKey) //0
  def name: Rep[String] = column[String]("USER_NAME") //1
  def mail: Rep[String] = column[String]("MAIL") //2
  def password_digest: Rep[String] = column[String]("PASSWORD")
  def created_at: Rep[DateTime]  = column[DateTime]("CREATED_AT") //3


  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, String, String, DateTime)] = (id, name, mail, password_digest, created_at)
}



