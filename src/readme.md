
# Groovy Sources

Please add your Groovy source files here.

Each service definition should be in a separate folder containing:
* service-def.json - describing the groovy service and dependant files
* Main service groovy file
* Service test groovy file
* service-help.html - documentation on the service

You may include common groovy classes that are used by multiple services
by adding them at the root level of this folder or by maintaining them
in sub-folders.

Note please ensure all groovy files are in the default package
(i.e. they do not have a package declaration) to avoid issues with the
type checker.
        