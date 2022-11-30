import asyncio, hashlib
from functionsserver import *
PORT = 6000
async def checkforquit(text, writer): 
    if text == "quit":
        writer.write("quit\n".encode("latin"))
        await writer.drain()
        return True 

async def Client(reader,writer):
    try:
        while True:
            command_request = (await reader.read(512)).decode("latin")
            writer.write("200\n".encode("latin"))
            await writer.drain()
            if await checkforquit(command_request, writer):
                return
            user_and_password = []
            if command_request == "login":
                for i in range(2):
                    response = (await reader.read(512)).decode("latin") # adds user and password to list
                    writer.write("200\n".encode("latin"))
                    await writer.drain()
                    if await checkforquit(response, writer):
                        return
                    user_and_password.append(response)
                benutzer = User().checkaccount(user_and_password[0], hashlib.md5(bytes(user_and_password[1], encoding='latin')).hexdigest())
                if benutzer.loggedin and benutzer.registriert:
                    writer.write("0\n".encode('latin'))
                    await writer.drain()
                    break
                elif benutzer.loggedin:
                    writer.write("1\n".encode('latin'))
                    await writer.drain()
                    break
                else:
                    writer.write("2\n".encode('latin')) 
                    await writer.drain()
        while True: 
            command_request = (await reader.read(512)).decode("latin")
            writer.write("200\n".encode("latin"))
            await writer.drain()
            if await checkforquit(response, writer):
                return
           
            if command_request == "getMes":
                user = (await reader.read(512)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return
                messages = benutzer.checkformessages(user)
                writer.write(messages.encode("latin")+"\n".encode("latin"))
                await writer.drain()
            if command_request == "proofuser":
                user = (await reader.read(512)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return

                if benutzer.searchaccount(user):
                    writer.write("1\n".encode('latin'))
                    await writer.drain()
                else:
                    writer.write("0\n".encode('latin'))
                    await writer.drain()
            if command_request == "sendMes":
                user = (await reader.read(512)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return
            
                messages = (await reader.read(5000)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(messages, writer):
                    return
                benutzer.insertmessage(user, messages) 
                writer.write("200\n".encode("latin"))
                await writer.drain()
            if data[0] == "createKey":
                user = (await reader.read(512)).decode("latin") #user
                writer.write("200\n".encode("latin"))
                if await checkforquit(user, writer):
                    return 
                status = benutzer.createKey(user).encode("latin")+"\n".encode("latin")
                if benutzer.status == 1:
                    writer.write("1\n".encode("latin"))
                    await writer.drain()
                    continue
                elif benutzer.status == 0:
                    writer.write(status)
                    await writer.drain()
                    P = (await reader.read(512)).decode(encoding="latin")
                    writer.write("200\n".encode("latin"))
                    await writer.drain()
                    G = (await reader.read(512)).decode(encoding="latin")
                    writer.write("200\n".encode("latin"))
                    await writer.drain()
                    A = (await reader.read(512)).decode(encoding="latin")
                    writer.write("200\n".encode("latin"))
                    await writer.drain()
                    benutzer.insertKeys(data[1], P, G, A)
                    print(f"{P:}{G:}{A:}")
                elif benutzer.status == 2:
                    writer.write(status)
                    await writer.drain()
                    P,G,A = benutzer.getKeys(data[1])
                    writer.write((P+"\n").encode("latin"))
                    await writer.drain()
                    writer.write((G+"\n").encode("latin"))
                    await writer.drain()
                    writer.write((A+"\n").encode("latin"))
                    await writer.drain()
                    B = (await reader.read(512)).decode(encoding="latin")
                    writer.write("200\n".encode("latin"))
                    await writer.drain()
                    benutzer.insertKeys(user=data[1], aorb = B)
                elif benutzer.status == 3:
                    writer.write(status)
                    await writer.drain()
                    P, B = benutzer.getKeys(data[1])
                    writer.write((P+"\n").encode("latin"))
                    await writer.drain()
                    writer.write((B+"\n").encode("latin"))
                    await writer.drain()

    except (ConnectionResetError, BrokenPipeError) as E:
        print(E)
        writer.close()
            
async def main():
    server = await asyncio.start_server(Client, 'localhost', PORT)
    async with server:
        await server.serve_forever()
if __name__ == '__main__':
    asyncio.run(main())
