package pt3;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMICalcServer implements RMICalcInterface {
	public int suma(int a, int b) throws RemoteException {
		System.out.println("Haciendo suma");
		return (a + b);
	}

	public int resta(int a, int b) throws RemoteException {
		System.out.println("Haciendo resta");

		return (a - b);
	}

	public int multip(int a, int b) throws RemoteException {
		System.out.println("Haciendo multiplicacio");

		return (a * b);
	}

	public int div(int a, int b) throws RemoteException {
		System.out.println("Haciendo divisio");

		return (a / b);
	}
	public int pow(int a, int b) throws RemoteException {
		System.out.println("Haciendo divisio");

		return (int) (Math.pow(a, b));
	}

	public static void main(String[] args) {
		Registry reg = null;
		try {
			reg = LocateRegistry.createRegistry(5555);
		} catch (Exception e) {
			System.out.println("ERROR: No se ha podido crear el registro");
			e.printStackTrace();
		}
		RMICalcServer serverObject = new RMICalcServer();
		try {
			reg.rebind("Calculadora", (RMICalcInterface) UnicastRemoteObject.exportObject(serverObject, 0));
		} catch (Exception e) {
			System.out.println("ERROR: No se ha podido inscribir el objeto servidor.");
			e.printStackTrace();
		}
	}
}