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
            data = []
            response = (await reader.read(512)).decode("latin")
            writer.write("200\n".encode("latin"))
            await writer.drain()
            if await checkforquit(response, writer):
                return
            data.append(response)
            if data[0] == "login":
                for i in range(2):
                    response = (await reader.read(512)).decode("latin")
                    writer.write("200\n".encode("latin"))
                    await writer.drain()
                    if await checkforquit(response, writer):
                        return
                    data.append(response)
                benutzer = User().checkaccount(data[1], hashlib.md5(bytes(data[2], encoding='latin')).hexdigest())
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
            data = []
            response = (await reader.read(512)).decode("latin")
            writer.write("200\n".encode("latin"))
            await writer.drain()
            if await checkforquit(response, writer):
                return
            
            data.append(response)
            if data[0] == "getMes":
                print("Get mes")
                response = (await reader.read(512)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(response, writer):
                    return
                data.append(response)
                nachrichte = benutzer.checkformessages(data[1])
                writer.write(nachrichte.encode("latin")+"\n".encode("latin"))
                await writer.drain()
            if data[0] == "proofuser":
                response = (await reader.read(512)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(response, writer):
                    return

                data.append(response)
                if benutzer.searchaccount(data[1]):
                    writer.write("1\n".encode('latin'))
                    await writer.drain()
                else:
                    writer.write("0\n".encode('latin'))
                    await writer.drain()
            if data[0] == "sendMes":
                response = (await reader.read(512)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(response, writer):
                    data.append(response)
                data.append(response)
            
                response = (await reader.read(5000)).decode("latin")
                writer.write("200\n".encode("latin"))
                await writer.drain()
                if await checkforquit(response, writer):
                    return
                data.append(response)
                benutzer.insertmessage(data[1], data[2]) 
                writer.write("200\n".encode("latin"))
                await writer.drain()
            if data[0] == "createKey":
                response = (await reader.read(512)).decode("latin") #user
                writer.write("200\n".encode("latin"))
                if await checkforquit(response, writer):
                    return 
                data.append(response)
                a = benutzer.createKey(data[1]).encode("latin")+"\n".encode("latin")
                if benutzer.status == 1:
                    print("1")
                    writer.write("1\n".encode("latin"))
                    await writer.drain()
                    continue
                elif benutzer.status == 0:
                    writer.write(a)
                    await writer.drain()
                    print("0")
                    P = (await reader.read(512)).decode(encoding="latin")
                    print(P)
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
                    writer.write(a)
                    await writer.drain()
                    print("2")
                    (P, G, A) = benutzer.getKeys(data[1])
                    writer.write(str(P).encode("latin"))
                    await writer.drain()
                    print("reached this point")
                    print("P got sent")
                    writer.write(str(G).encode("latin"))
                    await writer.drain()
                    print("reached G")
                    writer.write(str(A).encode("latin"))
                    await writer.drain()
                    print("reached A")
                    B = (await reader.read(512)).decode(encoding="latin")
                    writer.write("200\n".encode("latin"))
                    await writer.drain()
                    benutzer.insertKeys(B)
    except (ConnectionResetError, BrokenPipeError) as E:
        print(E)
        writer.close()
            
async def main():
    server = await asyncio.start_server(Client, 'localhost', PORT)
    async with server:
        await server.serve_forever()
if __name__ == '__main__':
    asyncio.run(main())
