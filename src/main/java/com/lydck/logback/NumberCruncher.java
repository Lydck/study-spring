package com.lydck.logback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NumberCruncher extends Remote {
	/**
	 * 获得整数的因数
	 */
	int[] factor(int number) throws RemoteException;
}
