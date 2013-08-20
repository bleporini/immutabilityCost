name := "Immutability Cost"

version:="1.0-SNASHOT"

scalaVersion:="2.10.2"

fork in Test := true

//javaOptions in Test += "-Xmx800m"

//javaOptions in Test += "-Xms800m"

//javaOptions in Test += "-XX:+PrintGCDetails"

//javaOptions in Test += "-Xloggc:/tmp/gc.log"

//javaOptions in Test += "-XX:+PrintCompilation"


libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"
