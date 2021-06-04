# JavaServices Development Tools

JSDT is a tool to generate Java classes from a Jolie interface and its types (used by its operations).

Given a Jolie file containing only interfaces and types, JSDT generates a class for a target interface and possibly the types used in its operations. The tool can also just generate the classes relative to a target Type.

JSDT generates Java-idiomatic classes to manage in a transparent way Jolie data structures (Values and ValueVectors). The generated classes use the [core](https://github.com/thesave/JSDT/tree/main/src/core/java/jsdt/core) classes `Single`, `MaybeSingle`, and `Multi` classes (which capture the cardinality of Jolie nodes) and the `BasicType` and `ChoiceType` classes (for representing basic and choice Jolie types).

For interfaces, JSDT minimises the boilerplate code developers need to write by generating a skeleton JavaService that implements the operations in the interface and that, as their first action, convert (`parse`) the request (a Jolie `Value`) into the relative JSDT-generated Java class.

For types, JSDT generates classes providing a `parse` (static) and `toValue` method to respetively instantiate a JSDT-generated class from a Jolie Value (e.g., useful to convert the Value parameter from an invocation) and, vice versa, to obtain a Jolie Value from an instance of a JSDT-generated class (e.g., to assemble the response for an invocation).

## Installation

Download the latest `jsdt-{version}.jar` file from the [release page](https://github.com/thesave/JSDT/releases).

## Dependencies

JSDT has no dependencies (the released jar contains them already)

The generated code has the following dependencies:

- [jolie](https://mvnrepository.com/artifact/org.jolie-lang/jolie);
- [libjolie](https://mvnrepository.com/artifact/org.jolie-lang/libjolie);
- [jolie-cli](https://mvnrepository.com/artifact/org.jolie-lang/jolie-cli);
- [jsdt-core](https://github.com/thesave/JSDT/releases) (from this repository, see the [release page](https://github.com/thesave/JSDT/releases)).

## Usage

### Example

To generate the JavaService class from the `MyInterface`  interface and its types from the `MyInterface.ol` file (e.g., [as found in the test folder](https://github.com/thesave/JSDT/tree/main/src/test/jolie)), using `src` as output destination, issue the command:

`java -jar jsdt-{version}.jar --compileTypes --dstDir=src MyInterface MyInterface.ol`

To compile the generated Java sources with javac use the command:

`javac -jar -cp jolie-{jolie-version}.jar:libjolie-{jolie-version}.jar:jsdt-core-{version}.jar src/MyInterface/*`

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

## Roadmap

- [ ] resolve type references
- [ ] resolve imports 
- [ ] support the full Jolie syntax
