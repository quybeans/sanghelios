// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server.authentication

package object protocol {
  final case class LoginRequest(
    username: String,
    password: String
  )

  final case class LoginResponse(
    token: String
  )
}
