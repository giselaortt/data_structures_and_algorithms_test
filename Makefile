clean:
	find -name '*.class' | xargs rm -rf {}
	find -name '*.zip' | xargs rm -rf {}
	find -name 'ex' | xargs rm -rf {}


zip:
	zip -r linked *.java *.in *.md *.pdf  Makefile


