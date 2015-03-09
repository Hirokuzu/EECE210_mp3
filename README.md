Machine Problem 3: Querying Music Catalogue 2.10
===


## Logistics

This machine problem is due November 3, 2014, by 11:00 a.m. and is worth 6% of the course grade.

## Background

In this machine problem, you will extend your work from the previous machine problem to support queries over the music catalogue.

Before starting with this machine problem, you should understand the following:
+ [Introduction to regular expressions](www.codeproject.com/Articles/939/An-Introduction-to-Regular-Expressions);
+ Trees and binary tree traversals;
+ Writing comparison methods for new classes/objects (e.g., overriding equals( ));
+ Understanding the use of [`hashCode`s](docs.oracle.com/javase/7/docs/api/java/lang/Object.html) and overriding `hashCode` for new classes;
+ Tokenization and parsing;
+ [Java collections](https://dl.dropboxusercontent.com/u/567187/EECE 210/Java/JavaCollectionsFramework.pdf) such as sets.

The skeleton code is written using `ANTLR4` and you should import the `antlr-4.4-complete.jar` file in Eclipse (`Projects > Properties > Java Build Path > Libraries > Add JARs…`). This JAR file can be found in the directory `src/ca/ubc/ece/eece210/mp3`.

## Queries over Music Catalogue 2.10

You will now implement a query functionality to the Catalogue. This will return a list of `Album`s that match a given criteria. We specify the criteria as a search rule (based on a very simple query language) that is executed against all the `Genre`s and albums in the catalogue.

A search rule is expressed as a string (query). For example:

`in ("Jazz") && by ("Louis Armstrong") || matches (".*Ripley.*")`

The first step in processing the query string is to identify the basic logical units (tokens) in the query. The query string would be tokenized as:

`<in> <(> <"Jazz"> <)> <&&> <by> <(> <"Louis Armstrong"> <)> <||> <matches> <(> <".*Ripley.*"> <")">`

Separate tokens are enclosed between `<` and `>`.

Notice that whitespace is ignored except when it is between the quotation marks. The tokenized query has only a linear structure. The tokenized query is then passed to the parser, which creates a tree representation of the query, called an abstract syntax tree (AST).

![Query Image](https://dl.dropboxusercontent.com/u/567187/eece%20210/Images/MP3/QueryExample.jpg)

The AST is produced according to a grammar, which is given in the following section. The grammar uniquely defines the AST generated from the query string.

Each node in the AST is a descendent of the `ASTNode` class. The `interpret( )` method of these classes is executed in a recursive manner by calling that method on the root node, which then calls that method on each of its children, and so on to the leaf nodes.

Let us walk through an example of executing our query string against a catalogue (represented in the figure below).

![Catalogue Image](https://dl.dropboxusercontent.com/u/567187/eece%20210/Images/MP3/CatalogueExample.jpg)

We will call the interpret function on the root node, "||" in the AST above. This is actually the `OrNode` node. The `OrNode` object first calls interpret on its children nodes: "&&" which is the `AndNode` node and "matches" which is an `InNode`. (You should read about basic binary tree traversals to understand what traversals are.)

We execute `interpret( )` on `AndNode` first. But `AndNode` has children too, so we execute `interpret( )` on its children and take the intersection of the result. The children of `AndNode` are `InNode` and `ByNode`.

`InNode` searches for all albums that contained in its argument – *Jazz*. In our diagram above, the albums are: *Louis and the Angels*, *Crossings*.

`ByNode` searches for albums written by the performer in the argument – *Louis Armstrong*. In our diagram above, the album is: *Louis and the Angels*.

`AndNode` intersects the results of its children and returns the result: *Louis and the Angels*.

So that takes care of the left subtree of `OrNode`. Now, let's move on to the right subtree – `MatchesNode`. `MatchesAST` searches for the title that matches its argument – *Ripley*. We treat its argument as a Java regular expression pattern and use the underlying regular expression implementation in Java to execute it. Running the regular expression *Ripley* on our current catalogue returns *The Talented Mr. Ripley*.

Now we are ready to take the union of the results from the left subtree and the right subtree of OrNode. So the final return value is the set *{Louis and the Angels} U {The Talented Mr. Ripley} = {Louis and the Angels, The Talented Mr. Ripley}*.

## Grammar

This is the grammar of the query language:

```
<orExpr> ::= <andExpr>(<or><andExpr>)*
<andExpr> ::= <atom>(<and><atom>)*
<atom> ::= <in>|<matches>|<by>|<LParen><orExpr><RParen>
<or> ::= "||"
<and> ::= "&&"
<in> ::= "in" <LParen><string><RParen>
<matches> ::= "matches" <LParen><string><RParen>
<by> ::= "by" <LParen><string><RParen>
<LParen> ::= "("
<RParen> ::= ")"
```

`<orExpr>` and `<andExpr>` are non-leaf nodes; `<in>`, `<matches>` and `<by>` are leaf nodes.

The argument to each leaf node can be interpreted as follows:
+ `in(Genre name)`
+ `matches(regular expression applied to title)`
+ `by(performer)`

## Examples

+ searching for albums by a performer that contain a string in the title: `by(<performer>) && matches(<some regex>)`
+ searching for albums by a performer and in a particular Genre: `by(<performer>) && in(<Genre>)`
+ searching for albums by a performer or in a particular Genre: `by(<performer>) || in(<Genre)`

*Run `QueryParserTest`*. Two tests should pass and one test should fail.

## Constructing our Parser

We will construct an LL recursive descent parser. Recursive descent means that the expression is parsed top-down using a set of mutually-recursive procedures (e.g., `orExpr`, `andExpr`). LL means that the parser processes the tokens from left to right, producing a leftmost derivation of the query.

More importantly, what you should know is that each non-terminal above – `<orExpr>`, `<andExpr>` and `<atom>` should be a method in `QueryParser` that will be called recursively.

The `andExpr` method has been implemented for you. You must complete the `orExpr` method to build the AST and also change the `getRoot` method so it calls the proper method.

## Implementation Details

### Implement functionality to support the "by" search query

First you will have to support the "by" query. To do this, create a class that extends `ASTNode`, add the implementation and then modify the parser and tokenizer to support the new element. This will mean, among others, that you have to create a new `TokenType`. Once you have done that, create tests that prove that your implementation works according to the specification.

### Override `equals()` and `hashcode()` for your classes

`Album`s are equal if they have the same performer and title. `Genre`s are equal if they have the same name. When you override `equals()` it is good practice to override `hashCode()` as well. See [this page](www.ibm.com/developerworks/java/library/j-jtp05273/index.html) for further information.

By the way, as a convenience, Eclipse can auto-generate `equals()` and `hashcode()` for simple cases. Go to `Source > Generate equals and hashcode`. You can use this feature but ensure that you also understand how to actually implement `equals()` and `hashcode()` on your own.

### Implement the `interpret()` methods of the `ASTNode` subclasses

The `interpret()` method of the `ASTNode` types is the method that actually performs some action to filter the `Album`s and `Genre`s according to the query string. This method will accept a `Catalogue` object as an argument and return a set of albums or genres that are selected from the catalogue.

### Implement the method `query(String queryString)` in the `Catalogue` class

You must add this method:
	`public List<Album> query(String queryString) {...}`

The method uses the infrastructure you wrote and tested above, and serves as an access point to it.

### Write JUnit tests to test the `Catalogue.query()` method

This means that you will have to write tests for the `interpret()` methods in `ASTNode` subclasses.

+ Test case #1: Create a test class for each leaf node (`InNode`, `MatchesNode` and `ByNode`) and test your implementation of the `interpret()` method.
+ Test case #2: Create a test class for each non-leaf node (`AndNode` and `OrNode`) that will test the interpret functionality. 
+ Test case #3: Create a test class that will exercise some complex queries that will make use of all the AST subclasses.

Please note that it is not about the quantity of the individual tests – it is about their quality. Think carefully about what you are testing and what inputs you should use.

> A good strategy for writing tests is to first write black-box tests that are based only on the specification. In fact, if you are really following extreme programming (XP) then you will have written these tests earlier. Then look at the code and use the white-box testing techniques to write some more tests. Probably the black-box tests are already testing most of the code, so you will not have to write many white-box tests, but this is a good way to make good tests.

## Submitting Your Work

You will submit your work by pushing all your code to your group’s private BitBucket repository `mp3`.

## Challenge Task

*If you seek an A+*: Expand the `Album` class to include the year in which the album was released. Then add additional support to the query mechanism to retrieve albums that were released in, before or after a given year in conjunction with other query terms. (You will need to extend the query grammar to support this operation.)
