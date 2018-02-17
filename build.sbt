name := "sanghelios"

version := "0.1"

scalaVersion := "2.12.4"

val circeVersion = "0.9.1"
val scalazVersion = "7.2.19"

mainClass in reStart := Some("com.helios.server.SangHelios")
addCommandAlias("dev", "~reStart")

lazy val circe = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-generic-extras",
).map(_ % circeVersion)

lazy val http = Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.0-RC2",
  "com.typesafe.akka" %% "akka-stream" % "2.5.9",
)

lazy val scalaz = Seq(
  "org.scalaz" %% "scalaz-core",
).map(_ % scalazVersion)

lazy val monix = Seq(
  "io.monix" %% "monix" % "2.3.3"
)

lazy val httpHelper = Seq(
  "de.heikoseeberger" %% "akka-http-circe" % "1.20.0-RC1",
  "ch.megard" %% "akka-http-cors" % "0.2.2"
)

val dependencies = Seq(circe, http, scalaz, monix, httpHelper).flatten

libraryDependencies := dependencies
