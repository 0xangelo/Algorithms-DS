JC = javac-algs4
JVM = java-algs4
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
        Pontes.java \
	Graph.java

MAIN = FasterWordLadders

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run: classes
	$(JVM) $(MAIN) words.utf-8.txt
