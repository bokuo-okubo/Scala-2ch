package controllers

import play.api.mvc._
import play.api._

/**
 * Created by bko on 8/5/15.
 */
class SessionsController extends Controller {

  def index = Action {
    Ok(<p>sessons controller index</p>)
  }

  def create = Action {
    Ok(<p>sessons controller index</p>)
  }
}
