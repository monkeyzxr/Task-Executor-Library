package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.*;

public class TaskExecutorImpl implements TaskExecutor 
{
	private TaskRunner[] threads;
	
	public TaskExecutorImpl(int threadNumber){
		threads	= new TaskRunner[threadNumber];
		try {
			//start N threads (given input threadNumber)
			for(int i = 0; i < threads.length; i++)
			{
				threads[i] = new TaskRunner();
				Thread thread = new Thread(threads[i]);
				thread.setName("TaskThread" + i);
				thread.start();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	public void addTask(Task task) 
	{
		try 
		{
			TaskRunner.getBlockingQueue().add(task);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}