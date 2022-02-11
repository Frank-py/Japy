
import socket, threading
PORT = 6000
<<<<<<< HEAD
while True:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(("", PORT))
        s.listen()
        print(f"listening on port {PORT}")
        conn, addr = s.accept()
        print("Waiting for clients")
        
        a = conn.recv(1024)
        conn.send(b"200\n")
        a = conn.recv(1024)
        conn.send(b"200\n")
        conn.send(b"1\n")
=======
    
def Client(conn, addr):
    try:
        while True:
            data = []
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            conn.send("1\n".encode('utf-8'))
            break
        while True: 
            data = []
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            data.append(conn.recv(512).decode(encoding="UTF-8"))
            conn.send(b"200\n")
            conn.send(b"1\n")
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
>>>>>>> 29d6136053255f6c3fdcd03d9f0e3e687ee82158
