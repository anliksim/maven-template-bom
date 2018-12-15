# Maven BOM Template

Template/example for multi-module maven projects that use and provide a bill of materials to manage project dependencies.

## Structure

    maven-template-bom
    ├── bom
    ├── module1
    │   ├── pom.xml
    │   └── src
    ├── module2
    │   ├── pom.xml
    │   └── src
    ├── pom.xml


The structure is simple. Every submodule is part of the aggregator, however, the `bom` module does (and must not) use a parent.

The project's own versions are handled by importing the BOM on the parent/top level. As a result `module2` does not have to set a version for it's declared dependency on `module1`. All internal versions will be taken from the BOM and resolve to `${project.version}`.