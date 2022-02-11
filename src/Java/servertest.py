import socket, threading
PORT = 6000
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind(("", PORT))
    s.listen()
    print(f"listening on port {PORT}")
    conn, addr = s.accept()
    print("Waiting for clients")
    while True:
        a = conn.recv(1024)
        conn.send(b"200\n")
        a = conn.recv(1024)
        conn.send(b"200\n")
        conn.send(b"1\n") 