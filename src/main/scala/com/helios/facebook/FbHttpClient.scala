// Copyright (C) 2018, Dau Van Quy, All rights reserved.

package com.helios.facebook

import scala.collection.immutable.Seq
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Failure
import scala.util.Success

import akka.http.scaladsl.HttpExt
import akka.http.scaladsl.model.FormData
import akka.http.scaladsl.model.HttpHeader
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.model.HttpProtocols
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.HttpResponse
import akka.stream.Materializer
import io.circe.Decoder
import io.circe.Json
import io.circe.generic.extras.Configuration
import io.circe.parser.parse
import monix.eval.Task

final class FbHttpClient(
  http: HttpExt
)(implicit
  val ec: ExecutionContext,
  val mat: Materializer
) {
  import FbHttpClient._

  private[this] def decodeResponse[A](raw: HttpResponse)(
    implicit decoder: Decoder[A]
  ): Task[A] = {
    Task.deferFuture {
      raw.entity.toStrict(5.seconds).flatMap { entity =>
        Future {
          parse(entity.data.utf8String) match {
            case Right(json) => decodeJson(json)
            case Left(ex) => throw ex
          }
        }
      }
    }
  }

  private[this] def decodeJson[A](
    json: Json
  )(implicit decoder: Decoder[A]
  ) = {
    decoder.decodeJson(json).toTry match {
      case Success(result) => result
      case Failure(ex) => throw ex
    }
  }

  def get[A](uri: String, payload: (String, String)*)(
    implicit decoder: Decoder[A]
  ): Task[A] = {
    val params = payload.toMap.map(_.productIterator.mkString("=")).mkString
    val request = new HttpRequest(
      uri = BASE_URL + uri + s"?$params",
      method = HttpMethods.GET,
      entity = FormData.Empty.toEntity,
      headers = Seq.empty[HttpHeader],
      protocol = HttpProtocols.`HTTP/2.0`
    )
    Task.deferFuture(http.singleRequest(request)).flatMap(decodeResponse(_)(decoder))
  }
}

object FbHttpClient {
  private val GRAPH_VERSION = "v2.2"
  val BASE_URL = s"https://graph.facebook.com/$GRAPH_VERSION/"
}
