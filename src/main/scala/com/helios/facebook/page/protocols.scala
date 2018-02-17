// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.facebook.page

import io.circe.Decoder
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._

object protocols {

  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames

  final case class GetAccountsResponse(
    data: Seq[FbPage]
  )

  object GetAccountsResponse {
    implicit val decoder: Decoder[GetAccountsResponse] = deriveDecoder
  }

  final case class FbPage(
    id: String,
    name: String,
    accessToken: String,
    category: String
  )

  object FbPage {
    implicit val decoder: Decoder[FbPage] = deriveDecoder
  }
}
