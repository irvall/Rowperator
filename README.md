# Rowperator
Simple Java program for making row operations on m x n matrices

### Getting started
Download `Rowperator.java` and run it in your terminal of choice.


#### With a custom matrix:
Run it like `java Rowperator m n i1 i2 ... i(m * n)` where `m` = rows, `n` = columns.

#### No arguments:
Starts of with a pseudo-randomly generated m x n matrix. These dimensions can be changed to your liking.

### Usage
`ri (+-s) rj` where `i` and `j` are the row number from 1 to and including `m`.

In your terminal, the matrix will be printed after each step. You should make simple calls on two rows at the time – adding `+`, subtracting `-` or swapping them `s`. A row can be prefixed with a constant `c` like `cri (+-) crj` if you wish to multiply the rows at the same time.

It's also possible to multiply or divide a single row in a single call, like so:

`rk (*/) c` where `k` is a row number and `c` a constant.
