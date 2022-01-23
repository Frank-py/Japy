import socket
from functionsserver import *
def getsock():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(("", 5000))
        s.listen()
        conn, addr = s.accept()
        data = conn.recv(1024).decode(encoding="UTF-8")
        data = data.split(" ")
        if data[0] == "register":
            connection = create_connection("localhost", "daniel", "4sdf38§$/WE3/FW§459fd2w3", "Messenger")
            register(connection, data[1:])
        elif data[0] == "login":
            connection = create_connection("localhost", "daniel", "4sdf38§$/WE3/FW§459fd2w3", "Messenger")
            login(connection, data[1:])
        elif data[0] == "updateip":
            connection = create_connection("localhost", "daniel", "4sdf38§$/WE3/FW§459fd2w3", "Messenger")
            updateip(connection, data[1:])
        elif data[0] == "insertmessage":
            connection = create_connection("localhost", "daniel", "4sdf38§$/WE3/FW§459fd2w3", "Messenger")
            insertmessage(connection, data[1:])
        elif data[0] == "fetchmessages":
            connection = create_connection("localhost", "daniel", "4sdf38§$/WE3/FW§459fd2w3", "Messenger")
            checkformessages(connection, data[1:])
        
while True:
    print(getsock())
#connection = create_connection("ip", "username", "passwort", "database")