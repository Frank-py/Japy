import socket, threading, hashlib
from functionsserver import *
PORT = 6000
    
def Client(conn, addr):
    while True:
        data = []
        try:
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            print(data)
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            print(data)
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            print(data)
            if data[0] == "login":
                benutzer = User().checkaccount(data[1], hashlib.md5(bytes(data[2], encoding='UTF-8')).hexdigest(), addr[0])
                if benutzer.loggedin and benutzer.registriert:
                    conn.send("0\n".encode('utf-8'))
                elif benutzer.loggedin:
                    conn.send("1\n".encode('utf-8'))
                else:
                    conn.send("2\n".encode('utf-8')) 
            else:
                conn.close()
                return
        except OSError:
            conn.send("4\n".encode('utf-8'))
            conn.close()
            print("error 1212")
            return
    
def main():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(("", PORT))
    while True:
        s.listen()
        conn, addr = s.accept()
        thread = threading.Thread(target = Client, args=(conn, addr))
        thread.start()
        print(f"Verbindungen: {threading.active_count() - 1}") #wegen main()
        thread.join()
if __name__ == '__main__':
    main()