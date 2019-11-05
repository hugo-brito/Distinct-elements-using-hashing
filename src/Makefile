all: compile_java run plots clean
compile_java:
	javac Ex1.java
	javac Ex2.java
	javac Ex3.java
	javac Ex4.java
	javac Experiment.java
	javac Main.java
clean:
	rm *.class
run:
	java Main > ../results/results.csv
plots:
	python3 plots.py
