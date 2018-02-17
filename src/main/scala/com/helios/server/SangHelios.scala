// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.HttpExt
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import monix.execution.Scheduler
import monix.execution.Scheduler.Implicits.global

object SangHelios extends App with Servers {

  lazy implicit val actor: ActorSystem = ActorSystem("sanghelios")
  lazy implicit val mat: ActorMaterializer = ActorMaterializer()
  lazy implicit val ec: Scheduler = global
  lazy implicit val httpExt: HttpExt = Http()

  val route = routes.foldLeft[Route](reject)(_ ~ _)
  Http().bindAndHandle(Route.handlerFlow(route), "0.0.0.0", 3012)
}
