import socket, threading, hashlib
from functionsserver import *
PORT = 6000
    
def Client(conn, addr):
    try:
        while True:
            data = []
            data.append(conn.recv(512).decode(encoding="utf-8"))
            conn.send("200\n".encode("utf-8"))
            if data[0] == "login":
                for i in range(2):
                    data.append(conn.recv(512).decode(encoding="utf-8"))
                    conn.send("200\n".encode("utf-8"))
                benutzer = User().checkaccount(data[1], hashlib.md5(bytes(data[2], encoding='utf-8')).hexdigest())
                print(benutzer)
                if benutzer.loggedin and benutzer.registriert:
                    conn.send("0\n".encode('utf-8'))
                    break
                elif benutzer.loggedin:
                    conn.send("1\n".encode('utf-8'))
                    break
                else:
                    conn.send("2\n".encode('utf-8')) 
        while True: 
            data = []
            data.append(conn.recv(512).decode(encoding="utf-8"))
            conn.send("200\n".encode("utf-8"))
            if data[0] == "getMes":
                print("getmes")
                data.append(conn.recv(512).decode(encoding="utf-8"))
                conn.send("200\n".encode("utf-8"))
                nachrichte = benutzer.checkformessages(data[1])
                conn.send(nachrichte.encode("utf-8")+"\n".encode("utf-8"))


            if data[0] == "proofuser":
                data.append(conn.recv(512).decode(encoding="utf-8"))
                conn.send("200\n".encode("utf-8"))
                if benutzer.searchaccount(data[1]):
                    conn.send("1\n".encode('utf-8'))
                else:
                    conn.send("0\n".encode('utf-8'))
            if data[0] == "sendMes":
                print("Nachrichtanfrage erfolgreich abgeschlossen")
                data.append(conn.recv(512).decode(encoding="utf-8"))
                conn.send("200\n".encode("utf-8"))
                data.append(conn.recv(5000).decode(encoding='utf-8'))
                conn.send("200\n".encode("utf-8"))
                print(data[2])
                benutzer.insertmessage(data[1], data[2]) 
                conn.send("200\n".encode("utf-8"))
            if data[0] == "createKey":
                data.append(conn.recv(512).decode(encoding="utf-8"))
                conn.send("200\n".encode("utf-8"))
                conn.send(benutzer.createKey(data[1]).encode("utf-8")+"\n".encode("utf-8"))
                if benutzer.status == 1:
                    continue
                if benutzer.status == 0:
                    P = conn.recv(512).decode(encoding="utf-8")
                    conn.send("200\n".encode("utf-8"))
                    G = conn.recv(512).decode(encoding="utf-8")
                    conn.send("200\n".encode("utf-8"))
                    A = conn.recv(512).decode(encoding="utf-8")
                    conn.send("200\n".encode("utf-8"))
                    benutzer.insertKeys(data[1], P, G, A)
                if benutzer.status == 2:
                    (P, G, A) = benutzer.getKeys(data[1])
                    conn.send(P.encode("utf-8"))
                    conn.send(G.encode("utf-8"))
                    conn.send(A.encode("utf-8"))
                    B = conn.recv(512).decode(encoding="utf-8")
                    conn.send("200\n".encode("utf-8"))
                    benutzer.insertKeys(B)

    except Exception as E:
        conn.send("4\n".encode('utf-8'))
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