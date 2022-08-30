clean:
	find -name '*.class' | xargs rm -rf {}
	find -name '*.zip' | xargs rm -rf {}
	find -name 'ex' | xargs rm -rf {}


zip:
	zip -r linked *.java *.in *.md *.pdf  Makefile


all:
	javac Algorithm1.java
	javac Algorithm2.java
	javac Algorithm3.java
	javac Algorithm4.java
	javac Algorithm5.java


run5:
	java Algorithm5 5.in


run4:
	java Algorithm4 4.in


run3:
	java Algorithm3 3.in


run2:
	java Algorithm2 2.in

