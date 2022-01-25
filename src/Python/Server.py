import socket, re, threading
from functionsserver import *
PORT = 5000

def Client(conn, addr):
    data = conn.recv(1024).decode(encoding="UTF-8")
    data = data.split(" ")
    if data[0] == "login":
        Benutzer = User().checkaccount(data[1], data[2], addr)
    
def main():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(("", PORT))
    s.listen()
    while True:
        conn, addr = s.accept()
        thread = threading.Thread(target = Client, args=(conn, addr))
        thread.start()
        print(f"Verbindungen: {threading.active_count() - 1}") #wegen main()
