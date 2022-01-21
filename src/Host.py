import socket

def getsock():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(("", 5000))
        s.listen()
        conn, addr = s.accept()
        data = conn.recv(1024).decode(encoding="UTF-8")
        return data
while True:
    print(getsock())        
 