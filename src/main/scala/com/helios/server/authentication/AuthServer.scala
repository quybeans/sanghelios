// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server.authentication

import scala.util.Success

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import com.helios.server.authentication.protocol.LoginRequest
import com.helios.server.generic.HeliosServer
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import monix.execution.Scheduler

final class AuthServer(
  implicit
  val ec: Scheduler,
  val mat: Materializer
) extends HeliosServer {

  val route: Route = (path("login") & entity(as[LoginRequest])) { request =>
    onComplete(services.auth.login(request.username, request.password).runAsync) {
      case Success(true) => complete("nice")
      case _ => complete("Login failed")
    }
  }
}
