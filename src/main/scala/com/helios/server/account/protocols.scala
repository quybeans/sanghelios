// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.server.account

import com.helios.facebook.page.protocols.FbPage

object protocols {
  final case class GetAllFbPageRequest(accessToken: String)

  final case class GetAllFbPageResponse(
    pages: Seq[FbPage]
  )
}
