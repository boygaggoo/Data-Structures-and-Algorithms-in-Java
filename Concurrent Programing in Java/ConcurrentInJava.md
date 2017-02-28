# Concurrent Programming in Java
## 1.Thread(线程) & Process(进程)
### 1.1 Process
* Process contains threads. Thread is lightweight
* Processes are independent of each other. Threads share common resources
* JVM stack are sharing coomon heap and method area
* For example, JVM is a process and main method is a thread

## 2. Concurrency(并发) & Parallelism(并行)
### 2.1 Concurrency & Parallelism
* Concurrency: A condition that exists when at least two threads are marking progress(doesn't necessarily mean they'will ever both be running at the same time instance). A more generalized form of parallelism that can include time-slicing as a form fo virtual parallelism
* Parallelism: A conditon that arises when at least two threads are executing simultaneously

## 3. How to Create a thread in Java 
* Instantiate a subclass wich extends the Thread class
* new the Thread constructor using subclass which implements the runnable interface

## 3.1 Create thread in two ways
### 3.1.1 run() vs start()
Always override run() abd call start()

* public void run(): no new thread will be created
* public void start(): a new thread will be created

### 3.1.2 Runnable vs Thread

### 3.1.3 Code Example
```
class Solution {
    public static void main (String[] args) {
        MyThread thread1 = new MyThread();
        Thread thread2 = new Thread(new MyRunnable());
        thread1.start();
        thread2.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run(){

    }
}

class MyRunnable implements Runnable{
    @Override
    public void run(){

    }
}
```




