kettle-fixture
==============

Allows you to write FitNesse fixtures using Kettle jobs and transformations.

To see an example, change to the sample directory, do

    java -jar /path/to/your/fitnesse.jar -o -p 8000

and browse to http://localhost:8080.

To build the package, you need the gradle build management tool (http://www.gradle.org). To build the distribution package, do

    gradle distTar

for a tarball or

    gradle distZip

for a ZIP archive and look in build/distribution.

