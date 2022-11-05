package com.goosemane;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Integer> queue;
    ArrayList<Integer> NumberOfPrimeNumbers = new ArrayList<Integer>();
    //for measuring time
    static long startTime = 0, endTime = 0;
    boolean firstProcessStarted = false;

    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<Integer>();
    }

    @Override
    public Integer getMessage() throws RemoteException {
        //recording start time
        if (!firstProcessStarted) {
            startTime = System.nanoTime();
        }
        //sign that process has started (for time elapse measurement)
        firstProcessStarted = true;
        return this.queue.poll();
    }

    @Override
    public void sendMessage(int num) throws RemoteException {
        this.queue.add(num);
    }

    @Override
    public void storeCalculatedMessage(int num) throws RemoteException {
        System.out.println("Queue consists of: " + queue);
        NumberOfPrimeNumbers.add(num);
        if (queue.isEmpty()) {
            try {
                Thread.sleep((long) 11.11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finalOutput(NumberOfPrimeNumbers);
        }
    }

    public static void finalOutput(ArrayList<Integer> numberList) {
        int sum = 0;
        for (int numbers : numberList) {
            sum += numbers;
        }
        System.out.println("The final sum is equal to: "+sum);
        endTime = System.nanoTime();
        System.out.println("Time taken:" + (endTime - startTime) );
    }

}