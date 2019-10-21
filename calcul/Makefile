# $Id: Makefile 505 2017-09-25 12:44:47Z coelho $

LEX	= jflex
YACC	= cup
JAVAC	= javac
JAVA	= java
JFLAGS	= -cp /usr/share/java/cup.jar:.

all: clean

compile:
	$(LEX) calcul.jlex
	$(YACC) -parser Parser calcul.cup
	$(JAVAC) $(JFLAGS) *.java

run: compile
	$(JAVA) $(JFLAGS) Parser

clean:
	$(RM) Lexer.java Parser.java sym.java *~ *.class
