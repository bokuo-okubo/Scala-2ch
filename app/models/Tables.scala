package models

import org.joda.time.DateTime
import org.joda.time._
import slick.driver.H2Driver.api._
import slick.lifted.{ProvenShape, ForeignKeyQuery}

import com.github.tototoshi.slick.H2JodaSupport._

/**
 * Created by bko on 8/5/15.
 */





case class User( ID:         Int,
                 name:       String,
                 email:      String,
                 password:   String,
                 created_at: DateTime )

class UserTable(tag: Tag) extends Table[User](tag, "users") {
  def ID         = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name       = column[String]("name")
  def password   = column[String]("password")
  def email      = column[String]("email")
  def created_at = column[DateTime]("CreatedAt")

  def * = (ID, name, password, email, created_at) <> (User.tupled, User.unapply)
}

object UserHandle {
  lazy val userQuery = TableQuery[UserTable]

  def create(user: User) {
    userQuery.insertOrUpdate(  )
  }
//
//  /**
//   * 更新
//   * @param customer
//   */
//  def update(customer: Customer)(implicit s: Session) {
//    customerQuery.filter(_.ID === customer.ID).update(customer)
//  }
//
//  /**
//   * 削除
//   * @param customer
//   */
//  def remove(customer: Customer)(implicit s: Session) {
//    customerQuery.filter(_.ID === customer.ID).delete
//  }
}
