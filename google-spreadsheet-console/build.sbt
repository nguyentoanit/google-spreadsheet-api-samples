import Dependencies._

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.google"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "google-spreadsheet-console",
    libraryDependencies += scalaTest % Test,
    // https://mvnrepository.com/artifact/com.google.apis/google-api-services-sheets
    libraryDependencies += "com.google.apis" % "google-api-services-sheets" % "v4-rev581-1.25.0"

  )
libraryDependencies += "com.google.apis" % "google-api-services-sheets" % "v4-rev581-1.25.0"
// https://mvnrepository.com/artifact/com.google.api-client/google-api-client
libraryDependencies += "com.google.api-client" % "google-api-client" % "1.30.2"
// https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client-jetty
libraryDependencies += "com.google.oauth-client" % "google-oauth-client-jetty" % "1.30.1"

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
