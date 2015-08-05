package models

import org.joda.time._
import play.api.db
import slick.driver.H2Driver.api._
import com.github.tototoshi.slick.H2JodaSupport._

import scala.concurrent.Future

/**
 * Created by bko on 8/5/15.
 */

case class User( ID:         Option[Long],
                 name:       String,
                 email:      String,
                 password:   String,
                 created_at: Option[DateTime])

class Users(tag: Tag) extends Table[User](tag, "users") {
  def ID:         Rep[Option[Long]]      = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def name:       Rep[String]           = column[String]("name")
  def password:   Rep[String]           = column[String]("password")
  def email:      Rep[String]           = column[String]("email")
  def created_at: Rep[Option[DateTime]] = column[Option[DateTime]]("CreatedAt")

  def * = (ID, name, password, email, created_at ) <> (User.tupled, User.unapply)
}


object UserHandle {
  lazy val userQuery = TableQuery[Users]
  lazy val db = Database.forConfig("h2mem1")

  def show_schema: Unit = {
    val schema = userQuery.schema
    schema.create.statements.foreach(println)
  }

  //TODO : test
  def create(user: User): Future[Int] = db.run(userQuery += user )

  // TODO : test
  def findByName(name: String) :Future[Option[models.User]] =
    db.run( userQuery.filter(_.name === name).result.headOption )
}
