// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server.account

import scala.util.Failure
import scala.util.Success

import akka.http.scaladsl.HttpExt
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import com.helios.server.generic.SocialModule
import com.helios.server.generic.HeliosServer
import monix.execution.Scheduler
import akka.http.scaladsl.server.Directives._
import com.helios.server.account.protocols.GetAllFbPageRequest
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

final class AccountServer(
  implicit
  val ec: Scheduler,
  val mat: Materializer,
  val httpExt: HttpExt
) extends HeliosServer with SocialModule {

  val route: Route = (post & path("page") & entity(as[GetAllFbPageRequest])) { request =>
    onComplete(socials.account.getAllAccounts(request.accessToken).runAsync) {
      case Success(response) => complete(response)
      case Failure(ex) => throw ex
    }
  }
}
