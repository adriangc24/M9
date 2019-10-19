package pt2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		InetAddress direcc = null;
		try {
			direcc = InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 40, (byte) 001 }); // Declaracion
																												// de la
																												// IP
																												// Servidor
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Host no encontrado :("); // control de excepcion
			System.exit(-1);
		}

		Socket sckt = null;
		DataInputStream dis = null;
		DataOutputStream dos = null; // Declaracion previa de sockets y streams
		Scanner sc = null;

		try {

			sc = new Scanner(System.in); // System.in para coger el input del cliente

			System.out.println("---- Bienvenido al piedra papel o tijera de AGonzalez y JGomez ----");

			while (true) {
				sckt = new Socket(direcc, 12346); // Declaramos el socket con la IP y el puerto
				dis = new DataInputStream(sckt.getInputStream()); // declaramos tanto inputstream como outputstream del
																	// socket
				dos = new DataOutputStream(sckt.getOutputStream());

				System.out.println("^^Introduce [Piedra|Papel|Tijeras] por teclado por favor^^");

				String aux = sc.next(); // cogemos el rol del cliente
				dos.writeInt(valor(aux)); // enviamos un int pasando por el metodo valor() (esta mas abajo)
				String resultado = dis.readUTF(); // almacenamos el resultado

				System.out.println(resultado);

				if (dis.readBoolean())
					break; // comprobacion de bandera para seguir o no
			}

		} catch (IOException e) {
			System.out.println("error: " + e + " -- Joan"); // control de error de lectura/escritura
		}
		try {
			if (sckt != null) { // comprobacion final para cerrar la conexion
				sckt.close();
				dis.close();
				dos.close();
				sc.close();
				System.out.println("Conexion terminada satisfactoriamente");
			}
		} catch (IOException e) {
			System.out.println("Error al cerrar el socket: " + e); // control de excepciones para cerrar conexion
		}

	}

	public static int valor(String aux) { // Metodo para devolver rol
		if (aux.equals("piedra"))
			return 1;
		else if (aux.toLowerCase().equals("papel"))
			return 2;
		else if (aux.toLowerCase().equals(("tijeras")) || aux.toLowerCase().equals("tijera"))
			return 3;
		return 0;
	}
}