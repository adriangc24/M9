package pt2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	static int puntosServer;
	static int puntosCliente;

	public static void main(String args[]) {
		puntosServer = 0;
		puntosCliente = 0;
		String resultado = "Resultado = Cliente : ";

		int randomNum = (int) (Math.random() * 3 + 1);
		System.out.println(randomNum);
		// Primero indicamsos la dirección IP local
		try {
			System.out.println("LocalHost = " + InetAddress.getLocalHost());
		} catch (UnknownHostException uhe) {
			System.err.println("No puedo saber la dirección IP local : " + uhe);
		}
		// Abrimos un "Socket de Servidor" TCP en el puerto 1234.
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(12346);
		} catch (IOException ioe) {
			System.err.println("Error al abrir el socket de servidor : " + ioe);
			System.exit(-1);
		}
		int entrada = 0;
		String salida;
		// Bucle infinito
		while (true) {
			try {
				// Esperamos a que alguien se conecte a nuestro Socket
				Socket sckt = ss.accept();
				// Extraemos los Streams de entrada y de salida
				DataInputStream dis = new DataInputStream(sckt.getInputStream());
				DataOutputStream dos = new DataOutputStream(sckt.getOutputStream());
				// Podemos extraer información del socket
				// Nº de puerto remoto
				int puerto = sckt.getPort();
				// Dirección de Internet remota
				// InetAddress direcc = sckt.getInetAddress();
				InetAddress direcc = InetAddress
						.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 40, (byte) 236 });

				// Leemos datos de la peticion
				/*
				 * 
				 * 1 - piedra 
				 * 2 - papel 
				 * 3 - tijeras
				 * 
				 * 
				 */

				// Recollim int enviat pel client
				entrada = dis.readInt();
				// Emmagatzem resultat en String
				resultado = "Cliente: " + calculo(entrada) + "\nServidor: " + calculo(randomNum) + "\nResultado: "
						+ juego(entrada, randomNum) + "\nPuntos Server: " + puntosServer + "\nPuntos Cliente: "
						+ puntosCliente;

				dos.writeUTF(resultado);
				
				//Generamos numero aleatorio
				randomNum = (int) (Math.random() * 3 + 1);
				
				// Limite puntos 3 reiniciamos puntos ya acabamos partidos
				if (puntosCliente == 3 || puntosServer == 3) {
					dos.writeBoolean(true);
					puntosServer=0;
					puntosCliente=0;
					
					dis.close();
					dos.close();
					sckt.close();
					System.exit(-1);
					
				} else {
					dos.writeBoolean(false);
				}
				// Escribimos el resultado
				// Cerramos los streams
				
			} catch (Exception e) {
				System.err.println("Se ha producido la excepción : " + e);
			}
		}
	}

	private static String juego(int entrada, int randomNum) {
		if (entrada == 1 && randomNum == 1) {
			return "empate";

		}
		if (entrada == 1 && randomNum == 2) {
			puntosServer++;
			return "Gana server";

		}
		if (entrada == 1 && randomNum == 3) {
			puntosCliente++;
			return "Gana cliente";

		}
		if (entrada == 2 && randomNum == 1) {
			puntosCliente++;
			return "Gana server";
		}
		if (entrada == 2 && randomNum == 2) {
			return "empate";
		}
		if (entrada == 2 && randomNum == 3) {
			puntosServer++;
			return "Gana server";
		}
		if (entrada == 3 && randomNum == 1) {
			puntosServer++;
			return "Gana server";
		}
		if (entrada == 3 && randomNum == 2) {
			puntosCliente++;
			return "Gana cliente";
		}
		if (entrada == 3 && randomNum == 3) {
			return "empate";
		}
		return null;
	}

	public static String calculo(int entrada) {

		if (entrada == 1) {
			return "piedra";
		}
		if (entrada == 2) {
			return "papel";
		}
		if (entrada == 3) {
			return "tijera";
		}
		return null;

	}
}
