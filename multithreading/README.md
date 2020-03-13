# Java Multithreading

## Task #1: Bank and Bank Users

A Bank class description:
1. private field moneyAmount;
2. public method transferMoney(int amount) - takes requested sum of money, throws exception in case of requested sum is bigger 
than total money amount;
3. public method hasEnoughMoney(int amount) - checks that requested sum is available.

In case of attempt to get more money than available the program should print the error and shut down.

A Bank User class description:
The Bank User runs in separate thread and tries to take transfer money periodically while it is possible.
The withdraw money from the account:
- the BankUser calls the hasEnoughMoney() method;
- the BankUser calls transferMoney() method in case if hasEnoughMoney() returned True.

To run several BankUser objects with same Bank object.
To analyze the reason of getting exception.

Solve the issue using:
-  low level java synchronization tools;
-  java.util.concurrent package.

## Task #2: The Dining Philosophers Problem

The Dining Philosophers problem is one of the classic problems used to describe synchronization issues in a multi-threaded environment 
and illustrate techniques for solving them.

Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.
Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both left and right forks. 
Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used by another philosopher. 
After an individual philosopher finishes eating, they need to put down both forks so that the forks become available to others. 
A philosopher can only take the fork on their right or the one on their left as they become available, and they cannot start eating before getting both forks. 
A philosopher can put the fork that he owns.
The taking the fork and putting it back to the table are separate actions that should be performed one by one.

The goal is to come up with a scheme/protocol that helps the philosophers achieve their goal of eating and thinking without getting starved to death.
Each philosopher is a thread with same logic.
The result of the task: output to the console of all philosopher's actions. 
E.g.: "philosopher#1 takes the fork#1", "philosopher#1 takes the fork#5", "philosopher#1 eats", "philosopher#1 puts the fork#1 to the table" and so on. 
The deadlock shouldn't happen.

## Task #3: Producer Consumer Problem

There are two processes, the producer and the consumer, who share a common, fixed-size buffer used as a queue. 
The producer's job is to generate data, put it into the buffer, and start again. 
At the same time, the consumer is consuming the data (i.e., removing it from the buffer), one piece at a time. 
The problem is to make sure that the producer won't try to add data into the buffer if it's full 
and that the consumer won't try to remove data from an empty buffer.

The task is to solve the issue using Blocking Queue and Thread Pool from java.util.concurrent package.

The Blocking Queue is shared fixed-size buffer.
Producer and Consumer are running in separate threads.

## Task #4: Thread Safe Singleton

Take a look at various implementation of the singleton pattern. 
Consider the lazy and eager initialization approaches. 
What is their pros and cons?

The result of this task should be implementation of thread safe singleton pattern with lazy initialization.

## Useful materials and links
1. Basic dive to the Multithreading in Java with nice examples.

- Book: Thinking in Java by Bruce Eckel, 4th Edition. "Concurrency" chapter.
- Book: Core Java Volume I--Fundamentals. Multithreading chapter.

2. The brief overview of the java.util.concurrent package.

- https://habr.com/ru/company/luxoft/blog/157273/
- https://habr.com/ru/post/277669/

3. The deep dive to the multithreading in Java mostly for learning in the future.

- Book: Java Concurrency in Practice, 1st Edition.
  https://www.amazon.com/Java-Concurrency-Practice-Brian-Goetz/dp/0321349601