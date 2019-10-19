package pt3;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICalcClient  {
	public static void main(String[] args) {
		RMICalcInterface calc = null;
		try {

			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 5555);
			calc = (RMICalcInterface) registry.lookup("Calculadora");
			System.out.println("SUMA: "+calc.suma(2, 2));
			System.out.println("RESTA: "+calc.resta(4, 1));
			System.out.println("MULTIPLICACIO: "+calc.multip(7, 2));
			System.out.println("DIVISIO: "+calc.div(100, 5));
			System.out.println("ELEVAR: "+calc.pow(9, 2));


		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}