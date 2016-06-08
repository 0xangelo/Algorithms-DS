JC = javac-algs4
JVM = java-algs4
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
       UniqueLRK.java

MAIN = UniqueLRK

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run: classes
	$(JVM) $(MAIN) 3
time: classes
	(time $(JVM) $(MAIN) 0 < pi-1million.txt) > saida.txt 2> tempos.txt
