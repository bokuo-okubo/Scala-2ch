package controllers

import org.joda.time.DateTime
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import slick._
import play.db._

import models.{UserHandle, User}

/**
 * Created by bko on 8/5/15.
 */


class UsersController extends Controller {

  var userDataForm: Form[User] = Form (
    mapping(
      "ID"         -> number,
      "name"       -> nonEmptyText(maxLength = 10),
      "email"      -> text, // TODO : validate
      "password"   -> nonEmptyText(maxLength = 10), // TODO : digest
      "created_at" -> jodaDate
    )(User.apply)(User.unapply)
  )

  def showCreatePage = Action{
    Ok(views.html.userIndex("user sing up"))
  }

  def create = Action { implicit request =>
    userDataForm.bindFromRequest.fold(
      errors => BadRequest(views.html.userIndex(errors.toString)),
      user => {
        UserHandle.create(user)
        Ok(views.html.userIndex(" SUCCESS!!! "))
      }
    )
  }
}
