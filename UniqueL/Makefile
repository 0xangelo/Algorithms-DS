JC = javac-algs4
JVM = java-algs4
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
       UniqueL.java

MAIN = UniqueL

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run: classes
	$(JVM) $(MAIN) 3
