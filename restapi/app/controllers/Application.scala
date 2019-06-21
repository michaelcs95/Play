package controllers
import javax.inject.Inject
import play.api.mvc._

class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def getName() = Action {
    Ok("You are awesome!")
  }


}