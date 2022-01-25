import socket, re, threading, hashlib
from functionsserver import *
PORT = 5000

def Client(conn, addr):
    while True:
        data = conn.recv(1024).decode(encoding="UTF-8")
        if data == "login":
            data = []
            for i in range(2):
                data.append(conn.recv(1024).decode(encoding="UTF-8"))
            benutzer = User().checkaccount(data[0], hashlib.md5(bytes(data[1], encoding='utf-8')).hexdigest(), addr[0])
            if isinstance(benutzer, str):
                print(benutzer)
            else:
                print("richtiges Passwort")
        else:
            print("test")
    conn.close()
    
def main():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(("", PORT))
    s.listen()
    while True:
        conn, addr = s.accept()
        thread = threading.Thread(target = Client, args=(conn, addr))
        thread.start()
        thread.join()
        print(f"Verbindungen: {threading.active_count() - 1}") #wegen main()
        break
main()
