import socket

def getsock():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(("", 5000))
        s.listen()
        conn, addr = s.accept()
        data = conn.recv(1024).decode(encoding="UTF-8")
        s.connect(("", 5000))
        if data[0] == "login":
            s.send(str(data))
        elif data[0] == "Nachricht":
            s.send(str(data))
        return data
while True:
    print(getsock())