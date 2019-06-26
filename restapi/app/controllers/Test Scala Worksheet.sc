
// We can define objects or classes and the worksheet
//will print the sesult of every expression
object Hello {
  def main(args:Array[String]) {
    println("Hello, Scala !!")
  }
}

println("Hello Scala")
val a = 4 + 5

case class CoachProfile(
  coachId: String,
  firstName: String,
  lastName: Option[String],
  profileImgUrl: Option[String],
  profileDescription: Option[String]
)


case class CoachProfileEntity(
  coachId: String,
  firstName: String,
  lastName: Option[String],
  profileImgUrl: Option[String],
  profileDescription: Option[String],
  chatAccessToken: String
) {

  def toCoachProfile: CoachProfile = CoachProfile(
    coachId = coachId,
    firstName = firstName,
    lastName = lastName,
    profileImgUrl = profileImgUrl,
    profileDescription = profileDescription
  )

  def toSalesforceCoachProfile: CoachProfile = CoachProfile(
    coachId = coachId,
    firstName = firstName,
    lastName = lastName,
    profileImgUrl = profileImgUrl,
    profileDescription = profileDescription,
    chatAccessToken = Some(chatAccessToken)
  )
}
