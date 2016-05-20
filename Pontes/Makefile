JC = javac-algs4
JVM = java-algs4
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
        Pontes.java \
	Graph.java

MAIN = Pontes

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run: classes
	$(JVM) $(MAIN)
