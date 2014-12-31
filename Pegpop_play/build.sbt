name := """Pegpop"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  ws,
  "org.neo4j" % "neo4j-jdbc" % "2.0.0-M06",
  "org.neo4j" % "neo4j" % "2.1.5" exclude("org.neo4j", "neo4j-jmx"),
  "org.mockito"%"mockito-core"%"1.9.5",
  "org.json"%"org.json"%"chargebee-1.0",
  "com.google.code.gson" % "gson" % "2.2.4"
)

resolvers ++= Seq(
  "Neo4j releases" at "http://m2.neo4j.org/content/repositories/releases",
  "Neo4j snapshots" at "http://m2.neo4j.org/content/repositories/snapshots"
)
