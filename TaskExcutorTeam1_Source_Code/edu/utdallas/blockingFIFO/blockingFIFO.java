package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;
import java.util.concurrent.locks.*;

public class blockingFIFO 
{
	final private int limit = 100;
	private int start = 0;
	private int end = 0;
	private Task[] queue = new Task[limit];
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	public void add(Task task) throws InterruptedException
	{
		lock.lock();
				
		try
		{
			while (isFull())
			{
				notFull.await();
			}			

			queue[end] = task;
			end = (end + 1) % limit;
		
			notEmpty.signal();						
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public Task take() throws InterruptedException
	{
		lock.lock();
		
		try
		{
			while (isEmpty())
			{
				notEmpty.await();
			}			

			Task task = queue[start];
			start = (start + 1) % limit;
		
			notFull.signal();
			
			return task;
		}
		finally
		{
			lock.unlock();
		}		
	}
	
	private boolean isFull()
	{
		if (start == (end + 1) % limit)
			return true;
		return false;
	}
	
	private boolean isEmpty()
	{
		if (start == end)
			return true;
		return false;
	}
	
}