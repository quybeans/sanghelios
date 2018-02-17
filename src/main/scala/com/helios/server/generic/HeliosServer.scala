// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server.generic

import akka.http.scaladsl.HttpExt
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import com.helios.facebook.FbHttpClient
import com.helios.facebook.page.FbPageService
import com.helios.server.Services
import com.helios.server.SocialServices
import com.helios.service.AuthService
import com.helios.service.SocialAccountService
import monix.execution.Scheduler

trait HeliosServer {
  implicit val ec: Scheduler
  implicit val mat: Materializer

  private val auth = new AuthService()

  val services: Services = new Services(auth)
  val route: Route
}

trait SocialModule {
  implicit val httpExt: HttpExt
  val fbHttp: FbHttpClient = new FbHttpClient(httpExt)

  implicit val mat: Materializer
  implicit val ec: Scheduler

  private val fbPage = new FbPageService(fbHttp)
  private val account = new SocialAccountService(fbPage)

  val socials: SocialServices = new SocialServices(account)
}

