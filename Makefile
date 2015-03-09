# Simple Makefile for compiling code

BINDIR = bin

JUNIT_DIR = ../../../lib/junit-4.11.jar
HAMCREST_DIR = ../../../lib/hamcrest-core-1.3.jar
ANTLR_DIR = src/ca/ubc/ece/eece210/mp3/antlr-4.4-complete.jar

CLASSPATH="$(BINDIR);$(ANTLR_DIR);$(JUNIT_DIR);$(HAMCREST_DIR)"

JAVACFLAGS=-d $(BINDIR) -cp $(CLASSPATH)
JAVARUNFLAGS = -cp $(CLASSPATH)

MAIN=ca.ubc.ece.eece210.mp3.grade.AllTests

SOURCES = $(shell find * -type f -name '*.java')

default: all

clean:
	rm -rf $(BINDIR)

# do not delete intermediates
.SECONDARY:

vars:
	@echo "SOURCES: $(SOURCES)"
	@echo "JAVAFLAGS: $(JAVACFLAGS)"

all: classes

classes: 
	javac $(JAVACFLAGS) $(SOURCES)
	
run:
	java $(JAVARUNFLAGS) $(MAIN) 