# Maven BOM Template

Template/example for multi-module maven projects that use and provide a bill of materials to manage project dependencies.

## Structure

    maven-template-bom
    ├── bom
    │   └── pom.xml (no parent)
    ├── module1
    │   ├── pom.xml
    │   └── src
    ├── module2
    │   ├── pom.xml
    │   └── src
    ├── pom.xml (aggegator)


The structure is simple. Every submodule is part of the aggregator, however, the `bom` module does (and must not) use a parent.

The project's own versions are handled by importing the BOM on the parent/top level. As a result `module2` does not have to set a version for it's declared dependency on `module1`. All internal versions will be taken from the BOM and resolve to `${project.version}`.


### Why bother

Why to use a BOM should be obvious. The approach described on the [Introduction to dependency mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html) is fine if you (or your organization) don't use a "super" parent pom. 

The setup I use is meant to provide a solution that works with plugins used for version manipulation and allows the use of a parent on the top-level pom.

#### Versions plugin

When using the versions plugin it's important to process all modules, as the `bom` module would be skipped otherwise as there is no parent-child relation. Please note this option is only available from version 2.5.

    mvn versions:set -DnewVersion=1.1-SNAPSHOT -DprocessAllModules=true
    
### Release plugin

When using the [maven-release-plugin](https://maven.apache.org/maven-release/maven-release-plugin/index.html) we also want the `bom` versions to be rolled correctly, which works because we added it to the `modules` section of the aggregator.

    mvn release:prepare -DdryRun=true -B

Run a `release:prepare` in dry run and check the generated pom files for the next and tag version.
