package controllers

import play.api._
import play.api.mvc._

/**
 * Created by bko on 8/5/15.
 */


class UsersController extends Controller {

  def index = Action {
    Ok(
    <p>OKだよ</p>
    )
  }
}
