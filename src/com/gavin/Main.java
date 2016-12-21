package com.gavin;

import java.io.*;
import java.util.*;
import gnu.io.*;

public class SimpleWrite
{
	static Enumeration portList;
	static CommPortIdentifier portId;
	static String messageString = "+++\n";
	static SerialPort serialPort;
	static OutputStream outputStream;
	static InputStream inputStream;
	public static void main(String[] args)
	{
		portList = CommPortIdentifier.getPortIdentifiers();
		System.out.println(portList.hasMoreElements());
		while (portList.hasMoreElements())
		{
			System.out.println("inside loop");
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
			{
				// if (portId.getName().equals("COM1")) {
				System.out.println(portId.getName());
				if (portId.getName().equals("COM5"))
				{
					try
					{
						serialPort = (SerialPort) portId.open("SimpleWriteApp", 2000);
					}
					catch (PortInUseException e)
					{
					}
					try
					{
						outputStream = serialPort.getOutputStream();
						inputStream = serialPort.getInputStream();
					}
					catch (IOException e)
					{
						System.out.println("IOException");
					}
					try
					{
						serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
					}
					catch (UnsupportedCommOperationException e)
					{
						System.out.println("UnsupportedCommOperationException");
					}
					try
					{
						byte[] bytes = new byte[10];
						outputStream.write(messageString.getBytes());
						for (int i = 0 ; i < 1000 ; i++)
						{}
						inputStream.read(bytes);
						System.out.println("hello");
					}
					catch (IOException e)
					{
						System.out.println("IOException");
					}
				}
			}
		}
	}
}
