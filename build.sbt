name := "enets"

version := "1.0"

scalaVersion := "2.12.8"

libraryDependencies  ++= Seq(
// "ai.h2o" % "h2o-ext-mojo-pipeline" % "3.40.0.4", 
// "ai.h2o" % "h2o-genmodel-ext-xgboost" % "3.40.0.4", 
// "ai.h2o" % "h2o-genmodel" % "3.40.0.4",
"org.apache.spark" % "spark-core_2.12" % "3.4.0",
"org.apache.spark" % "spark-mllib_2.12" % "3.4.0",
"org.apache.spark" % "spark-sql_2.12" % "3.4.0", 
"org.apache.httpcomponents" % "httpclient" % "4.5.14" ,
"com.opencsv" % "opencsv" % "5.3"
)


scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
        