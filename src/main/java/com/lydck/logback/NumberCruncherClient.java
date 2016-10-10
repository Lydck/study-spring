package com.lydck.logback;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NumberCruncherClient {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost");
		NumberCruncher lookup = (NumberCruncher) registry.lookup("Factor");
		int[] factor = lookup.factor(120);
		for (int i : factor) {
			System.out.print(i + " ");
		}
	}
}
