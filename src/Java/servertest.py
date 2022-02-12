
import socket, threading
PORT = 6000
    
def Client(conn, addr):
    try:
        while True:
            data = []
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            if data[0] == "login":
                data.append(conn.recv(512).decode(encoding="UTF-8"))
                conn.send(b"200\n")
                data.append(conn.recv(512).decode(encoding="UTF-8"))
                conn.send(b"200\n")
                conn.send("1\n".encode('utf-8'))
            if data[0] == "proofuser":
                data.append(conn.recv(512).decode(encoding="UTF-8"))
                conn.send(b"200\n")
                conn.send("1\n".encode('utf-8'))
    except OSError:
        conn.send("4\n".encode('utf-8'))
        conn.close()
        print("error")
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
