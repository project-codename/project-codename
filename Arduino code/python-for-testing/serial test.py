import serial
import time

"""
This is just a class that (pretty badly) re-implements Serial the Monitor in Arduino IDE. (For some reason).
"""
ser=serial.Serial('COM8', 9600, timeout=0)
ser.write(b"test");
while True:
    try:
        data = ser.readline().decode('utf-8')
        if data=='': continue

        print(data)
        time.sleep(1)
    except serial.SerialTimeoutException:
        print('fuck')
        time.sleep(1)
