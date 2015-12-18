package main.scala.rest.model

import org.junit.{After, Test, Before}


class EnrollEventsTest {

  val events = new Events()

  var event:Map[String,String] = Map[String,String]()

  var enrollEvents= new EnrollEvents()

  var createEvent= new Events()

  @Before
  def init(){

    event += "eventName" -> "myFirstEvent"
    event += "organization" -> "amazinglyAwesomeOrganization"
    event += "from" -> "startDate"
    event += "to" -> "endDate"
    event += "location" -> "address"
    event += "volunteersRequired" -> "5"
    event += "usersParticipating" -> "0"
    event += "commentsPosted" -> ""
    event += "comments" -> ""
    event += "expired" -> "false"

//    event += "volunteersContact" -> ""
    event += "volunteersInterested" -> "0"
    event += "locationNearby" -> "San Francisco"
    event += "category" -> "environment"
    event += "description" -> "some description goes here"
    event += "isActive" -> "true"
    event += "isApproved" -> "true"
    event += "isAwaitingAcceptance" -> "false"

    createEvent.createEvent(event)

  }

  @Test
  def itShouldIncreaseTheCountOfUsersParticipating(){

    //given

    var user:Map[String,String] = Map[String,String]()

    user += "username" -> "apurv"
    user += "emailId" -> "emailId"

    val expected = true
    val basicEvent= event - "usersParticipating" - "volunteersInterested" -"volunteersRequired" -"volunteersContact"

    //when
    val actual = enrollEvents.enrollAsParticipant(basicEvent)

    //then
    assert(actual == expected)
  }


  @Test
  def itShouldAddVolunteersToVolunteersContact(){

    //given

    var user:Map[String,String] = Map[String,String]()

    user += "username" -> "apurv"
    user += "emailId" -> "emailId"

    val expected = true
    val basicEvent= event - "usersParticipating" - "volunteersInterested" -"volunteersRequired"


    //when

    val actual = enrollEvents.enrollAsVolunteer(basicEvent,user)

    //then
    assert(actual == expected)
  }

  @After
  def clearDb(){
    createEvent.mongodbConnection.dropAllDataFrom("createEvents")
  }

}
