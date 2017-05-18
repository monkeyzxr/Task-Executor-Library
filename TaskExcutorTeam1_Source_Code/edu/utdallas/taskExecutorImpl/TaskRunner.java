package edu.utdallas.taskExecutorImpl;

import edu.utdallas.blockingFIFO.blockingFIFO;
import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable 
{
	private static blockingFIFO blockingQueue = new blockingFIFO();

	public static blockingFIFO getBlockingQueue() 
	{
		return blockingQueue;
	}

	public void run()
	{
		while (true)
		{
			try 
			{
				//get a task from the BlockingQueue
				Task newTask = blockingQueue.take();
				newTask.execute();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}