name := """scala-2ch"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test
)

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.h2database" % "h2" % "1.3.175",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",

  // to use datetime for slick.
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.0.0",

  "org.scalikejdbc" %% "scalikejdbc"        % "2.2.7",
  "org.scalikejdbc" %% "scalikejdbc-test"   % "2.2.7"   % "test",
  "org.scalikejdbc" %% "scalikejdbc-config"  % "2.2.7",
  "com.h2database"  %  "h2"                 % "1.4.187",
  "ch.qos.logback"  %  "logback-classic"    % "1.1.3"
)

fork in run := true

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
