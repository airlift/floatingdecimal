A fork of openjdk's FloatingDecimal to fix monitor contention when parsing doubles due to a static synchronized method.

To use, prepend the jar file to the boot classpath:

```
java -Xbootclasspath/p:floatingdecimal-0.1.jar ...
```
