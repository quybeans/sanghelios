// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.service

import com.helios.facebook.page.FbPageService
import monix.eval.Task
import monix.execution.Scheduler

final class SocialAccountService(
  fbPage: FbPageService
)(implicit
  val ec: Scheduler
) {

  def getAllAccounts(token: String): Task[String] = {
    fbPage.getAllFbPage(token)
  }
}
