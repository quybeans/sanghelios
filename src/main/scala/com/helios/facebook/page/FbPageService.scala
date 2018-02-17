// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.facebook.page

import akka.stream.Materializer
import com.helios.facebook.FbHttpClient
import com.helios.facebook.page.protocols._
import monix.eval.Task
import monix.execution.Scheduler

final class FbPageService(
  fbHttp: FbHttpClient,
)(implicit
  val mat: Materializer,
  val scheduler: Scheduler
) {

  def getAllFbPage(token: String): Task[Seq[FbPage]] = {
    fbHttp.get[GetAccountsResponse]("me/accounts/", "access_token" -> token)
      .map(_.data)
  }
}
