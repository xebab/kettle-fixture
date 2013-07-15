kettle-fixture
==============

Allows you to write FitNesse fixtures using Kettle jobs and transformations.

To see an example, unpack the distribution archive, change to the sample directory and do

    java -jar /path/to/your/fitnesse.jar -o -p 8000

Then, browse to http://localhost:8080.

To build the distribution package, you need the gradle build management tool (http://www.gradle.org). Build the package with

    gradle distTar

for a tarball or

    gradle distZip

for a ZIP archive and look in build/distribution.

