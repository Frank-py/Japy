import socket, threading, hashlib
from functionsserver import *
PORT = 6000
    
def Client(conn, addr):
    try:
        while True:
            data = []
            data.append(conn.recv(512).decode(encoding="latin-1"))
            conn.send("200\n".encode("latin-1"))
            if data[0] == "login":
                for i in range(2):
                    data.append(conn.recv(512).decode(encoding="latin-1"))
                    conn.send("200\n".encode("latin-1"))
                benutzer = User().checkaccount(data[1], hashlib.md5(bytes(data[2], encoding='latin-1')).hexdigest(), addr[0])
                if benutzer.loggedin and benutzer.registriert:
                    conn.send("0\n".encode('latin-1'))
                    break
                elif benutzer.loggedin:
                    conn.send("1\n".encode('latin-1'))
                    break
                else:
                    conn.send("2\n".encode('latin-1')) 
        while True: 
            data = []
            data.append(conn.recv(512).decode(encoding="latin-1"))
            conn.send("200\n".encode("latin-1"))
            if data[0] == "proofuser":
                data.append(conn.recv(512).decode(encoding="latin-1"))
                conn.send("200\n".encode("latin-1"))
                conn.send(benutzer.searchaccount(data[1]).encode('latin-1')+"\n".encode('latin-1'))
            else:
                conn.close()
                return
    except Exception as E:
        conn.send("4\n".encode('latin-1'))
        conn.close()
        print(E)
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