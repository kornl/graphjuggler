SHELL = /bin/sh

.SUFFIXES:
.SUFFIXES: .java .class

all: doc/graphjuggler.dvi Start.class

.java.class:
	javac $<

doc/graphjuggler.dvi: doc/graphjuggler.tex
	cd doc && latex graphjuggler

clean:
	rm -f doc/*.dvi
	rm -f *.class