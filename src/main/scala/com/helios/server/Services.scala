// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server

import scala.concurrent.ExecutionContext

import com.helios.service.AuthService
import com.helios.service.SocialAccountService

final class Services(
  val auth: AuthService
)(implicit
  ex: ExecutionContext
)

final class SocialServices(
  val account: SocialAccountService
)
