# Java Services Development Tools

JSDT is a tool to generate Java classes from a Jolie interface and its types (used by its operations).

Given a Jolie file containing only interfaces and types, JSDT generates a class for a target interface and possibly the types used in its operations. The tool can also just generate the classes relative to a target Type.

JSDT generates Java-idiomatic classes to manage in a transparent way Jolie data structures (Values and ValueVectors). The generated classes use the [core](https://github.com/thesave/JSDT/tree/main/src/core/java/jsdt/core) classes `Single`, `MaybeSingle`, and `Multi` classes (which capture the cardinality of Jolie nodes) and the `BasicType` and `ChoiceType` classes (for representing basic and choice Jolie types).

For interfaces, JSDT minimises the boilerplate code developers need to write by generating a skeleton JavaService that implements the operations in the interface and that, as their first action, convert (`parse`) the request (a Jolie `Value`) into the relative JSDT-generated Java class.

For types, JSDT generates classes providing a `parse` (static) and `toValue` method to respetively instantiate a JSDT-generated class from a Jolie Value (e.g., useful to convert the Value parameter from an invocation) and, vice versa, to obtain a Jolie Value from an instance of a JSDT-generated class (e.g., to assemble the response for an invocation).

## Installation

Install through jpm via `jpm add @thesave/jsdt_core`.

Otherwise, download the latest `jsdt-{version}.jar` file from the [release page](https://github.com/thesave/JSDT/releases).

## Dependencies

JSDT has no dependencies (the released jar contains them already)

The generated code has the following dependencies:

- [jolie](https://mvnrepository.com/artifact/org.jolie-lang/jolie) 1.10.4+;
- [libjolie](https://mvnrepository.com/artifact/org.jolie-lang/libjolie); 1.10.4+
- [jolie-cli](https://mvnrepository.com/artifact/org.jolie-lang/jolie-cli) 1.10.4+;
- [jsdt-core](https://github.com/thesave/JSDT/releases) (from this repository, see the [release page](https://github.com/thesave/JSDT/releases)).

## Usage

### Simple Example

To generate the JavaService class from the `MyInterface`  interface and its types from the `MyInterface.ol` file (e.g., [as found in the test folder](https://github.com/thesave/JSDT/tree/main/src/test/jolie)), using `src` as output destination, issue the command:

`java -jar jsdt-{version}.jar --compileTypes --dstDir=src MyInterface MyInterface.ol`

To compile the generated Java sources with javac use the command:

`javac -jar -cp jolie-{jolie-version}.jar:libjolie-{jolie-version}.jar:jsdt-core-{version}.jar src/MyInterface/*`

### Using JSDT, JPM, and Gradle to manage JavaService projects

When building Jolie JavaService, it's useful to use Maven or Gradle for the Java part (libraries, compilation, and release preparation) and JPM for the Jolie part (package publishing). Let us see a mildly opinionated way of setting up a JavaService project.
Here, for conciseness, we see an example with Gradle, but the concepts hold also for Maven projects, which have a similar structure.

#### JPM

First, create the `jpm.json` file for the release with the command `jpm init` in the root of the project. 
Open the `jpm.json` file and set the key `"version"` as `"@VERSION@"` (i.e., `"version": "@VERSION@"`) and the key
`"distJar"` as `"projectName-@VERSION@.jar"`. 
This is useful to use gradle as the single place where we set the package version number — we will see we are going to replace the token `@VERSION@` in `makeRelease` task of our `build.gradle` file.
Fill the other information in the `jpm.json` file as needed — package name, author, (execution) dependencies (as an example, [see here](https://github.com/thesave/liquidService/blob/master/jpm.json), etc.

#### Gradle

Now, let us pass to the `build.gradle` file, which we create using the `gradle init` command, and fill with the structure below.

Comments explain the interesting lines.

```gradle
plugins {
    id 'java'
    id 'java-library'
}

repositories {
    mavenCentral()
}

version "0.1" // the current version of the JavaService
def libDir = file( "lib" ) // the location of the local (as in non-maven-published) dependencies that must be included in the project release
def releaseDir = file( "release" ) // the folder where we will find the files to publish the releases of the JavaService on JPM

dependencies {
    implementation fileTree( dir: libDir, include: '*.jar' ) // here we are including the local dependencies
    implementation 'org.jolie-lang:jolie:1.10.4'
    implementation 'org.jolie-lang:libjolie:1.10.4'
    implementation 'org.jolie-lang:jolie-cli:1.10.4'
    // use e.g., maven central to get the lines for the other dependencies of this package
}

compileJava   { // not strictly necessary, but useful to make sure the released JavaService is compliant with its target Jolie versions
  sourceCompatibility = '11'
  targetCompatibility = '11'
}

jar {
    enabled = true
      
    manifest {
        attributes 'Main-Class': 'MyJavaService' // this is the name of the main class that implements the JavaService
    }
}

import org.apache.tools.ant.filters.ReplaceTokens

task makeRelease() { // the release task that automates compilation and assembly of the release files 
    tasks.compileJava.mustRunAfter( clean )
    dependsOn( clean )
    dependsOn( build )
    doLast{
        copy {
            from( buildDir.toPath().resolve( "libs" ) ){  // this is the JavaService project jar
                include "*.jar"
            }
            into releaseDir.toPath().resolve( "dist" ) 
        }
        copy {
            from( libDir ){ // these are the local libraries 
                include "*.jar" 
            }
            into releaseDir.toPath().resolve( "dist" )
        }
        copy { // these are the other files for the release
            from( projectDir ){
                include "jpm.json"
                include "main.ol" // the main.ol file is the Jolie file that describes the types, interfaces, and services of the JavaService
                                  // to include more than these files use the `include` command as seen here for the other files
                include "README.md"
                filter( ReplaceTokens, tokens: [ VERSION: version ] ) // this is where we replace the token `@VERSION@` with the content of version
            }
            into releaseDir
        }
    }
}

clean.doFirst{
    delete releaseDir
}
```

When using JSDT, remember to download the [jsdt-core](https://github.com/thesave/JSDT/releases) from this repository and put the jar under the `lib` folder in the root of the project.

The settings above follow the typical structure where the code of the JavaService is under `src/main/java` from the root of the project.

When done with the implementation, issuing the command `gradle makeRelease` assembles all the needed files into the ``release`` folder.
To release a new version of the JavaService, it is sufficient to enter the ``release`` folder and issue the command ``jpm publish`` (make sure you are logged into npm before issuing the command).

#### JSDT

Let us pass to JSDT. First write the `main.ol` Jolie file that describes the types and interfaces of the JavaService (you can define the embedding command afterwards, e.g., when you finished some stage of the Java implementation).

Now, you can download the [jsdt](https://github.com/thesave/JSDT/releases) executable jar in the root of the project and execute the command

```java -jar jsdt-x.y.z.jar --package=namespace.projectName --compileTypes --dstDir=src/java/main main.ol MyInterface```

where `x.y.z` is the download version of JSDT, `namespace.projectName` is the package path of the project (optional), and `MyInterface` is the name of the interface of the JavaService. The `--compileTypes` is an optional parameter instructing JSDT to also generate the classes from the Jolie types of the interface into structured Java objects.

The generated classes are under the usual `src/java/main` folder. As a user of JSDT, you just need to modify (and rename, if needed) the file `MyInterfaceService.java`, which already performs the parsing of the requests from Jolie into the generated Java classes.

Note that JSDT also generates the classes for the response types. Hence, if, e.g., the name of the response type of an operation is called `MyResponseType`, you can build the response using `MyResponseType` Java class (and its subclasses) and send back the response to Jolie as a Value with the method `toValue()` of the assembled object.

### Complete commands

```
Usage: jsdt [-hV] [--compileTypes] [--type] [--dstDir=<dstDir>][--package=<packageName>] <file> <symbolName>
JavaService Development Tool
      <file>                  The .ol file containing the type(s) and/or interface(s) to compile.
      <symbolName>            The name of the symbol source target of the compilation. By default it is an interface.
      --compileTypes          Compile also the types used by the target interface.
      --dstDir=<dstDir>       The path of the destination directory of the generated Java classes.
  -h, --help                  Show this help message and exit.
      --package=<packageName> The name of the package of the generated Java classes.
      --type                  Indicates that the target symbol is a type, instead of an interface.
  -V, --version               Print version information and exit.
```
