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
            data = []
            response = (await reader.read(512)).decode("utf-8")
            writer.write("200\n".encode("utf-8"))
            await writer.drain()
            if await checkforquit(response, writer):
                return
            data.append(response)
            if data[0] == "login":
                for i in range(2):
                    response = (await reader.read(512)).decode("utf-8")
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    if await checkforquit(response, writer):
                        return
                    data.append(response)
                benutzer = User().checkaccount(data[1], hashlib.md5(bytes(data[2], encoding='utf-8')).hexdigest())
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
            data = []
            response = (await reader.read(512)).decode("utf-8")
            writer.write("200\n".encode("utf-8"))
            await writer.drain()
            if await checkforquit(response, writer):
                return
            
            data.append(response)
            if data[0] == "getMes":
                print("Get mes")
                response = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(response, writer):
                    return
                data.append(response)
                nachrichte = benutzer.checkformessages(data[1])
                writer.write(nachrichte.encode("utf-8")+"\n".encode("utf-8"))
                await writer.drain()
            if data[0] == "proofuser":
                response = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(response, writer):
                    return

                data.append(response)
                if benutzer.searchaccount(data[1]):
                    writer.write("1\n".encode('utf-8'))
                    await writer.drain()
                else:
                    writer.write("0\n".encode('utf-8'))
                    await writer.drain()
            if data[0] == "sendMes":
                response = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(response, writer):
                    data.append(response)
                data.append(response)
            
                response = (await reader.read(5000)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(response, writer):
                    return
                data.append(response)
                benutzer.insertmessage(data[1], data[2]) 
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
            if data[0] == "createKey":
                response = (await reader.read(512)).decode("utf-8") #user
                writer.write("200\n".encode("utf-8"))
                if await checkforquit(response, writer):
                    return
                data.append(response)
                a = benutzer.createKey(data[1]).encode("utf-8")+"\n".encode("utf-8")
                if benutzer.status == 1:
                    print("1")
                    writer.write("1\n".encode("utf-8"))
                    await writer.drain()
                    continue
                elif benutzer.status == 0:
                    writer.write(a)
                    await writer.drain()
                    print("0")
                    P = (await reader.read(512)).decode(encoding="utf-8")
                    print(P)
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    G = (await reader.read(512)).decode(encoding="utf-8")
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    A = (await reader.read(512)).decode(encoding="utf-8")
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    benutzer.insertKeys(data[1], P, G, A)
                    print(f"{P:}{G:}{A:}")
                elif benutzer.status == 2:
                    writer.write(a)
                    await writer.drain()
                    print("2")
                    (P, G, A) = benutzer.getKeys(data[1])
                    writer.write(P.encode("utf-8"))
                    await writer.drain()
                    writer.write(G.encode("utf-8"))
                    await writer.drain()
                    writer.write(A.encode("utf-8"))
                    await writer.drain()
                    B = (await reader.read(512)).decode(encoding="utf-8")
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    benutzer.insertKeys(B)
    except (ConnectionResetError, BrokenPipeError) as E:
        print(E)
        writer.close()
        await writer.wait_closed()
            
async def main():
    server = await asyncio.start_server(Client, 'localhost', PORT)
    async with server:
        await server.serve_forever()
if __name__ == '__main__':
    asyncio.run(main())
