# JavaServices Development Tools

JSDT is a tool to generate Java classes from a Jolie interface and its types (used by its operations).

## Usage

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
