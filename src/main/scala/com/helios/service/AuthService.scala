// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.service

import monix.eval.Task
import monix.execution.Scheduler

final class AuthService()(
  implicit
  val ec: Scheduler
) {

  def login(username: String, password: String): Task[Boolean] = {
    (username, password) match {
      case ("admin", "admin") => Task.now(true)
      case _ => Task.now(false)
    }
  }
}
