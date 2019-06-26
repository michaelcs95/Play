package controllers

import play.api.libs.ws._
import play.api.libs.json._
import com.fasterxml.jackson.databind.JsonNode
import scala.concurrent._
import ExecutionContext.Implicits.global

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(ws: WSClient, cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  case class Book(title: String, authors: Seq[Author])
  case class Author(name: String)

  case class Invoice(name: String, address: String, idNumber: String, code: String)


  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def hello(name:String) = Action {implicit request: Request[AnyContent] =>
    //Ok("Hello, " + name + "!" )
    Ok(views.html.hello.render( name ))
  }

  def plainText() = Action {
    Ok("This is just a text message.")
  }

  def jsonMap = Action {
    implicit val authorFormat = Json.format[Author]
    implicit val bookFormat = Json.format[Book]
    //val books = Seq(Book("Book One",Seq(Author("Author One"))),Book("Book One",Seq(Author("Author One"))))
    val books = (Book("Book One",Seq(Author("Author One"))))
    val json: JsValue = Json.parse("""
  {
    "name" : "Watership Down",
    "location" : {
      "lat" : 51.235685,
      "long" : -1.309197
    },
    "residents" : [ {
      "name" : "Fiver",
      "age" : 4,
      "role" : null
    }, {
      "name" : "Bigwig",
      "age" : 6,
      "role" : "Owsla"
    } ]
  }
  """)
    //val books = Seq("Book 1", "Book 2")
    Ok(json)
    // Ok(Json.toJson(books))
  }

  def jsonObject() = Action {

    implicit val invoiceFormat = Json.format[Invoice]
    val invoice: Invoice = new Invoice("Perico de los Palotes", "City", "123456-7", "002245");

    Ok(Json.toJson(invoice));
  }

  def jsonCatch() = Action.async {
    ws.url("http://localhost:8000/json/object").get().map { response =>
      Ok(response.body)
    }
  }





}
