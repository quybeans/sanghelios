// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server

import akka.http.scaladsl.HttpExt
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.helios.server.account.AccountServer
import com.helios.server.authentication.AuthServer
import monix.execution.Scheduler

trait Servers {
  implicit val ec: Scheduler
  implicit val mat: ActorMaterializer
  implicit val httpExt: HttpExt

  val routes: Seq[Route] = Seq(
    new AuthServer,
    new AccountServer
  ).map(_.route)
}
