// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.service

import com.helios.facebook.page.FbPageService
import com.helios.facebook.page.protocols.FbPage
import monix.eval.Task
import monix.execution.Scheduler

final class SocialAccountService(
  fbPage: FbPageService
)(implicit
  val ec: Scheduler
) {

  def getAllAccounts(token: String): Task[Seq[FbPage]] = {
    fbPage.getAllFbPage(token)
  }
}
