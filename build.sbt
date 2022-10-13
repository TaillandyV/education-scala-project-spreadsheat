val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Spreadsheet", // TODO: name your project
    version := "0.1.0-SNAPSHOT",
    developers := List( // TODO: replace the following developer by your team developers
      Developer(
        id    = "AdamGouja",
        name  = "Adam Gouja",
        email = "adam.gouja@edu.esiee.fr",
        url   = url("https://github.com/AdamGouja")
      ),
      Developer(
        id    = "TaillandyV",
        name  = "Valentin Taillandy",
        email = "valentin.taillandy@edu.esiee.fr",
        url   = url("https://github.com/TaillandyV")
      ),
      Developer(
        id    = "AdelKhalil",
        name  = "Adel Khalil",
        email = "adel.khalil@edu.esiee.fr",
        url   = url("https://github.com/khalila95")
      ),
    ),
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.29" % Test)
  )
