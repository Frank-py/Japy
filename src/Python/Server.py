import socket, threading, hashlib
from functionsserver import *
PORT = 5000
    
def Client(conn, addr):
    while True:
        data = []
        try:
            data = conn.recv(1024).decode(encoding="UTF-8")
            print(data = conn.recv(1024).decode(encoding="UTF-8"))
            data = conn.recv(1024).decode(encoding="UTF-8")
            print(data = conn.recv(1024).decode(encoding="UTF-8"))
            data = conn.recv(1024).decode(encoding="UTF-8")
            print(data = conn.recv(1024).decode(encoding="UTF-8"))
            if data == "login":
<<<<<<< HEAD
                benutzer = User().checkaccount(data[0], hashlib.md5(bytes(data[1], encoding='UTF-8')).hexdigest(), addr[0])
                if benutzer.loggedin and benutzer.registriert:
                    conn.send("0\n".encode('utf-8'))
                elif benutzer.loggedin:
                    conn.send("1\n".encode('utf-8'))
                else:
                    conn.send("2\n".encode('utf-8'))
=======
                try:
                    data = []
                    data.append(conn.recv(1024).decode(encoding="UTF-8"))
                    data.append(conn.recv(1024).decode(encoding="UTF-8"))
                    print(data)
                    conn.send("1\n".encode('utf-8'))
                except Exception:
                        conn.send("4\n".encode('utf-8'))
                #benutzer = User().checkaccount(data[0], hashlib.md5(bytes(data[1], encoding='UTF-8')).hexdigest(), addr[0])
               # if benutzer.loggedin and benutzer.registriert:
                #    conn.send("0\n".encode('utf-8'))
               # elif benutzer.loggedin:
               #     conn.send("1\n".encode('utf-8'))
               # else:
               #     conn.send("2\n".encode('utf-8'))
>>>>>>> 12c1ee418626a2e347285e3bb396939856864dca
            else:
                conn.close()
        except OSError:
            conn.send("4\n".encode('utf-8'))
            conn.close()
            return
    
def main():
    while True:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(("", PORT))
        s.listen()
        conn, addr = s.accept()
        thread = threading.Thread(target = Client, args=(conn, addr))
        thread.start()
        print(f"Verbindungen: {threading.active_count() - 1}") #wegen main()
        thread.join()
if __name__ == '__main__':
    main()