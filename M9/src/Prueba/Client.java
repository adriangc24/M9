package Prueba;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String args[]) {
		// Leemos el primer parámetro, donde debe ir la dirección
		// IP del servidor
		InetAddress direcc = null;
		byte[] diooos = new byte[] { (byte) 192, (byte) 168, (byte) 40, (byte) 1 };
		try {
			direcc = InetAddress.getByAddress(diooos);
		} catch (UnknownHostException uhe) {
			System.err.println("Host no encontrado : " + uhe);
			System.exit(-1);
		}
		int puerto = 12348; // Puerto que hemos usado para el servidor

		for (int n = 1; n < 100; n++) { // Para cada uno de los argumentos...
			Socket sckt = null;
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try { // Convertimos el texto en número
				int numero = 4;// Convertimos el texto en número
				sckt = new Socket(direcc, puerto);
				dis = new DataInputStream(sckt.getInputStream()); // Creamos el Socket
				dos = new DataOutputStream(sckt.getOutputStream());// Extraemos los streams de entrada y salida
				dos.writeInt(numero); // Lo escribimos
				final long resultado = dis.readLong(); // Leemos el resultado
				System.out.println("Solicitud = " + numero + "\tResultado = " + resultado);// Indicamos en pantalla
				// y cerramos los streams y el socket
			} catch (Exception e) {
				System.err.println("Se ha producido la excepción : " + e);
			}
			try {
				if (sckt != null) {
					sckt.close();
					dos.close();
					dis.close();
				}
			} catch (IOException ioe) {
				System.err.println("Error al cerrar el socket : " + ioe);
			}
		}
	}

}
