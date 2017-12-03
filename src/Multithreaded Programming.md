### Executor and ExecutorService
- Java Thread Pool is a performance solution for scaling system
- work with Java Thread Pool by ExecutorService and Executors
- java.util.concurrent.ExecutorService provides methods to manage termination and methods to produce a Future for tracking progress of tasks
- java.util.concurrent.Executors class provides factory methods for the executor services
- Methods:
- public static ExecutorService newFixedThreadPool(int nThreads): creates a thread pool for reusing a fixed number of threads . At any point if having any tasked are submitted when all threads are active, they will wait until a thread is available.
- public static ExecutorService newSingleThreadExecutor(): creates an Executor that uses a single worker thread. Tasks are guaranteed to execute sequentially, and no more than one task will be active at a specific time.
– Difference between newFixedThreadPool(1) & newSingleThreadExecutor(): Executor of newSingleThreadExecutor() factory method guaranteed not to be reconfigurable at runtime.
– If a thread terminates(both: newFixedThreadPool() & newSingleThreadExecutor()) due to a failure during execution prior to shutdown, a new one will created for executing subsequent tasks.
- void java.util.concurrent.Executor.execute(Runnable command): executes the given command at some time in the com.dbbyte.future. The command may execute in a new thread, in a pooled thread, or in the calling thread, at the discretion of the Executor implementation.
- void java.util.concurrent.ExecutorService.shutdown(): setup a shutdown right after all submitted tasks are executed, and not accepted any new task.
- boolean java.util.concurrent.ExecutorService.isTerminated(): returns true if all tasks have completed after shut down.; isTerminated is always false unless either shutdown or shutdownNow was called first.


### Java Future
- Java Future with Callable is very important for tasks which we want to know status and get the returned object
- The Callable interface is similar to Runnable, which are designed for classes’ instances executed by thread. However, Callable can return a result and throw a checked exception.
- We execute Callable in a thread pool by using an Executor, which provides methods to manage termination or produce a Future for asynchronous tasks.
- Executor submit method with Callable as parameter return a Future object: Future<String> com.dbbyte.future = executor.submit(new Callable<String>() {...});
- a Future represents the result of an asynchronous computation. It has methods to check completion, or wait for completion. The result of the completion can be retrieved using method get when it completes, and is blocked until it is ready.
- The V get(...) method will throws:
    – CancellationException if the computation was cancelled
    – ExecutionException if the computation threw an exception
    – InterruptedException if the current thread was interrupted while waiting
    – TimeoutException if the wait timed out
    
### Java CompletableFuture
- we have the concept of a Future object, which can help us make something done while waiting for other things
- although we can inspect Future objects to see if they’re done, what if we wanna execute some code whenever its ready? We still have to wait until the result is available
- CompletableFuture meets the requirement, and more than that
- We use one of those factory methods to create a CompletableFuture object
static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier);
static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor);
static CompletableFuture<Void> runAsync(Runnable runnable);
static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor);
- If we don’t provide any Executor object as input parameter, the method will use ForkJoinPool.commonPool()
- If we wanna do more things after the result is available, CompletableFuture has thenApply() method:
- Just like supplyAsync() method above, we can apply those operations asynchronously in different thread pool by using:
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn);
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor);

Complete a com.dbbyte.future
- thenApply() return a CompletableFuture object with specific Type, but thenAccept() returns a CompletableFuture<Void>
- We don’t need a thenAccept() as final stage to make the com.dbbyte.future object work at the end. It just helps us do more things. But we need complete method or get method to make a final actually.
- The complete method sends the result to the com.dbbyte.future despite transformation that the operations have done before.
- Just like Java Future, we use get() to retrieve the result of the completion when it completes:
  




  
