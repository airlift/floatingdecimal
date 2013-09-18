A fork of openjdk's FloatingDecimal to fix monitor contention when parsing doubles due to a static synchronized method. There's a permanent fix for this issue in Java 8 (http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7032154)

To use, prepend the jar file to the boot classpath:

```
java -Xbootclasspath/p:floatingdecimal-0.1.jar ...
```
