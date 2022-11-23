import socket
PORT = 6000

def main():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)    
    server.connect(("", PORT))
    while True:
        try:
            text = input(">> ")
            server.send(text.encode('utf8'))
            response = server.recv(255).decode('utf8')
            if response == "quit":
                server.close()
                return
            print(response)
            
        except KeyboardInterrupt:
            server.close()
            return




if __name__ == '__main__':
    main()
    print("finished")