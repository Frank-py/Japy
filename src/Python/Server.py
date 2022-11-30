import asyncio, hashlib
from functionsserver import *
PORT = 6000
async def checkforquit(text, writer): 
    if text == "quit":
        writer.write("quit\n".encode("utf-8"))
        await writer.drain()
        return True 

async def Client(reader,writer):
    try:
        while True:
            command_request = (await reader.read(512)).decode("utf-8")
            writer.write("200\n".encode("utf-8"))
            await writer.drain()
            if await checkforquit(command_request, writer):
                return
            user_and_password = []
            if command_request == "login":
                for i in range(2):
                    response = (await reader.read(512)).decode("utf-8") # adds user and password to list
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    if await checkforquit(response, writer):
                        return
                    user_and_password.append(response)
                benutzer = User().checkaccount(user_and_password[0], hashlib.md5(bytes(user_and_password[1], encoding='utf-8')).hexdigest())
                if benutzer.loggedin and benutzer.registriert:
                    writer.write("0\n".encode('utf-8'))
                    await writer.drain()
                    break
                elif benutzer.loggedin:
                    writer.write("1\n".encode('utf-8'))
                    await writer.drain()
                    break
                else:
                    writer.write("2\n".encode('utf-8')) 
                    await writer.drain()
        while True: 
            command_request = (await reader.read(512)).decode("utf-8")
            writer.write("200\n".encode("utf-8"))
            await writer.drain()
            if await checkforquit(response, writer):
                return
           
            if command_request == "getMes":
                print("getMes")
                user = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return
                messages = benutzer.checkformessages(user)
                print(messages)
                writer.write((messages+"\n").encode("utf-8"))
                await writer.drain()
                continue
            elif command_request == "proofuser":
                print("proofuser")
                user = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return

                if benutzer.searchaccount(user):
                    writer.write("1\n".encode('utf-8'))
                    await writer.drain()
                else:
                    writer.write("0\n".encode('utf-8'))
                    await writer.drain()
            elif command_request == "sendMes":
                print("sendMes")
                userMes = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return
            
                messages = (await reader.read(5000)).decode("utf-8")
                print(messages)
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(messages, writer):
                    return
                benutzer.insertmessage(userMes, messages) 
            elif command_request == "createKey":
                print("createKey")
                user = (await reader.read(512)).decode("utf-8") #user
                print("user = ", user)
                writer.write("200\n".encode("utf-8"))
                if await checkforquit(user, writer):
                    return 
                status = benutzer.createKey(user).encode("utf-8")+"\n".encode("utf-8")
                if benutzer.status == 1:
                    writer.write("1\n".encode("utf-8"))
                    await writer.drain()
                    continue
                elif benutzer.status == 0:
                    writer.write(status)
                    await writer.drain()
                    P = (await reader.read(512)).decode(encoding="utf-8")
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    G = (await reader.read(512)).decode(encoding="utf-8")
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    A = (await reader.read(512)).decode(encoding="utf-8")
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    benutzer.insertKeys(user, P, G, A)
                    print(f"{P:}{G:}{A:}")
                elif benutzer.status == 2:
                    writer.write(status)
                    await writer.drain()
                    print("sent", status)
                    P,G,A = benutzer.getKeys(user)
                    writer.write((P+"\n").encode("utf-8"))
                    await writer.drain()
                    print("sent P " + P)
                    writer.write((G+"\n").encode("utf-8"))
                    await writer.drain()
                    print("sent G")
                    writer.write((A+"\n").encode("utf-8"))
                    await writer.drain()
                    print("sent A")
                    B = (await reader.read(512)).decode(encoding="utf-8")
                    print("B", B)
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    benutzer.insertKeys(user=user, aorb = B)
                elif benutzer.status == 3:
                    print("3")
                    writer.write(status)
                    await writer.drain()
                    P, B = benutzer.getKeys(user)
                    writer.write((P+"\n").encode("utf-8"))
                    await writer.drain()
                    writer.write((B+"\n").encode("utf-8"))
                    await writer.drain()
                print(status)
                    

    except (ConnectionResetError, BrokenPipeError) as E:
        print(E)
        writer.close()
            
async def main():
    server = await asyncio.start_server(Client, 'localhost', PORT)
    async with server:
        await server.serve_forever()
if __name__ == '__main__':
    asyncio.run(main())
