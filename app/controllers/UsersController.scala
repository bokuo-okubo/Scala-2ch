package controllers

import org.joda.time.DateTime
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import slick._
import play.db._

import models.{UserHandle, User}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by bko on 8/5/15.
 */


class UsersController extends Controller {

  val userDataForm: Form[User] = Form (
    mapping(
      "id"         -> optional(longNumber),
      "name"       -> nonEmptyText(maxLength = 10),
      "email"      -> text, // TODO : validate
      "password"   -> nonEmptyText(maxLength = 10),// TODO : digest
      "created_at" -> optional(jodaDate)
    )(User.apply)(User.unapply)
  )

  def showCreatePage = Action{
    Ok(views.html.userIndex("user sing up"))
  }

  def create = Action { implicit request =>
    userDataForm.bindFromRequest.fold(
      errors => {
        UserHandle.show_schema
        BadRequest(views.html.userIndex(errors.toString))
      },
      form => {
        val user = User(None, form.name, form.password, form.email, None)
        UserHandle.show_schema
        println( UserHandle.findByName(form.name) )
        UserHandle.create(user)
        Ok(views.html.userIndex(" SUCCESS!!! ")).withSession( "login" -> "yes" )
      }
    )
  }
}
