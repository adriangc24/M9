package pt3;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class CalculatorServer implements RMICalcInterface{

	public CalculatorServer() {
		try {
			Calculator c = new CalculatorImpl();
			Naming.rebind("rmi://127.0.0.1:5555/CalculatorService", c);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}

	public static void main(String args[]) {
		new CalculatorServer();
		
	}

	@Override
	public int suma(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int resta(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multip(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int div(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
}
