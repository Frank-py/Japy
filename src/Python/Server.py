import socket, re
from functionsserver import *
def getsock():
    connection = create_connection("localhost", "daniel", "4sdf38§$/WE3/FW§459fd2w3", "Messenger")
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(("", 5000))
        s.listen()
        conn, addr = s.accept()
        data = conn.recv(1024).decode(encoding="UTF-8")
        data = data.split(" ")
        if data[0] == "register":
            register(connection, data[1:])
        elif data[0] == "login":
            login(connection, data[1:])
        elif data[0] == "updateip":
            updateip(connection, data[1:])
        elif data[0] == "insertmessage":
            insertmessage(connection, data[1:])
        elif data[0] == "checkformessages":
            a = re.sub(r'[^A-Za-z0-9_]', '', data[1])
            return checkformessages(connection, data[1].strip())
        elif data[0] == "checkaccount":
            if checkaccount(connection, data[1]):
                login(connection, data[1:])
            else:
                register(connection, data[1:])
        else:
            return checkformessages(connection, data[1])
while True:
    print(getsock())