import socket
from functionsserver import *
def getsock():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(("", 5000))
        s.listen()
        conn, addr = s.accept()
        data = conn.recv(1024).decode(encoding="UTF-8")
        
while True:
    print(getsock())
#connection = create_connection("ip", "username", "passwort", "database")