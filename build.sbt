name := "artGallery"
version := "1.0-SNAPSHOT"
herokuAppName in Compile := "artgallerytm"
lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)
scalaVersion := "2.11.6"

libraryDependencies += filters
libraryDependencies += evolutions

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"

libraryDependencies ++= Seq(
  javaJdbc,  
  cache,
  javaWs,
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "bootstrap" % "3.3.5",
   "com.feth" %% "play-easymail" % "0.7.0-SNAPSHOT",
  "org.mindrot" % "jbcrypt" % "0.3m")
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
resolvers += Resolver.sonatypeRepo("snapshots")