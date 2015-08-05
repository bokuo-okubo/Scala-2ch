package controllers

import play.api.mvc._
import play.api._

/**
 * Created by bko on 8/5/15.
 */
class SessionsController extends Controller {

  def index = Action {
    Ok(views.html.loginIndex("user login"))
  }

  def showCreatePage = Action{
    Ok(views.html.userIndex("user sing up"))
  }

  def create = Action { request =>
    request.session.get("login").map{ login =>
      Ok(<p>sessons controller index login: {login}</p>)
    }.getOrElse{
      Ok(<p>not connected</p>)
    }
  }
}
