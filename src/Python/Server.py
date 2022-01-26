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
            benutzer = User().checkaccount(data[0], hashlib.md5(bytes(data[1], encoding='UTF-8')).hexdigest(), addr[0])
            if benutzer.loggedin and benutzer.registriert:
                print("registriert")
                conn.send("2".encode('utf-8'))
            elif benutzer.loggedin:
                print("richtiges Passwort")
                conn.send("1".encode('utf-8'))
            else:
                print("falsches Passwort")
                conn.send("0".encode('utf-8'))
        else:
            conn.send(b"hallo")
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
