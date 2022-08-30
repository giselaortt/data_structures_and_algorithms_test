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


5:
	javac Algorithm5.java
	java Algorithm5 5.in


4:
	javac Algorithm4.java
	java Algorithm4 4.in


3:
	javac Algorithm3.java
	java Algorithm3 3.in


2:
	javac Algorithm2.java
	java Algorithm2 2.in

