import asyncio, bcrypt
from functionsserver import *
PORT = 6000
MAX_MESSAGE_LENGTH = 1000
RESPONSE_VALUE = "200\n".encode("utf-8")

async def checkforquit(text, writer): 
    if text == "quit" or text == "logout":
        writer.write(RESPONSE_VALUE)
        await writer.drain()
        writer.close()

async def Client(reader,writer):
    try:
        while True:
            command_request = (await reader.read(512)).decode("utf-8")
            if await checkforquit(command_request, writer):
                return
            writer.write(RESPONSE_VALUE)
            await writer.drain()
            user_and_password = []
            if command_request == "login":
                for i in range(2):
                    response = (await reader.read(512)).decode("utf-8") # adds user and password to list
                    if await checkforquit(response, writer):
                        return
                    writer.write(RESPONSE_VALUE)
                    await writer.drain()
                    user_and_password.append(response)
                password = user_and_password[1]
                salt = bcrypt.gensalt()
                hashed_password = bcrypt.hashpw(password.encode('utf-8'), salt)
                benutzer = User()
                Exist = benutzer.check_account(user_and_password[0])
                if not Exist:
                    benutzer.register(user_and_password[0], hashed_password.decode("utf-8"))
                else:
                    benutzer.login(user_and_password[0], password) 
                if benutzer.logged_in and benutzer.registered:
                    writer.write("0\n".encode('utf-8'))
                    await writer.drain()
                    #benutzer.fetch_friends()
                    break
                elif benutzer.logged_in:
                    writer.write("1\n".encode('utf-8'))
                    await writer.drain()
                    break
                else:
                    writer.write("2\n".encode('utf-8')) 
                    await writer.drain()
        while True: 
            
            command_request = (await reader.read(512)).decode("utf-8")
            if await checkforquit(response, writer):
                return
            writer.write(RESPONSE_VALUE)
            await writer.drain()

            if command_request == "getMes":
                
                user = (await reader.read(512)).decode("utf-8")
                if await checkforquit(user, writer):
                    return
                writer.write(RESPONSE_VALUE)
                await writer.drain()

                anfangswert = (await reader.read(512)).decode("utf-8")
                if await checkforquit(anfangswert, writer):
                    return
                writer.write(RESPONSE_VALUE)
                await writer.drain()
                endwert = (await reader.read(512)).decode("utf-8")
                if await checkforquit(endwert, writer):
                    return
                writer.write(RESPONSE_VALUE)
                await writer.drain()
                messages = benutzer.checkformessages(recv = user, anfang = int(anfangswert), ende = int(endwert))
                
                writer.write((messages+"\n").encode("utf-8"))
                await writer.drain()
                continue
            elif command_request == "proofuser":
                
                user = (await reader.read(512)).decode("utf-8")
                if await checkforquit(user, writer):
                    return
                writer.write(RESPONSE_VALUE)
                await writer.drain()

                if benutzer.search_account(user):
                    writer.write("1\n".encode('utf-8'))
                    await writer.drain()
                else:
                    writer.write("0\n".encode('utf-8'))
                    await writer.drain()
            elif command_request == "sendMes":
                
                userMes = (await reader.read(512)).decode("utf-8")
                if await checkforquit(user, writer):
                    return
                writer.write(RESPONSE_VALUE)
                await writer.drain()
            
                messages = (await reader.read(10000)).decode("utf-8")
                if await checkforquit(messages, writer):
                    return
                if len(messages) > MAX_MESSAGE_LENGTH:
                    writer.write("300\n".encode('utf-8'))
                    continue
                else:
                    writer.write(RESPONSE_VALUE)
                await writer.drain()
                benutzer.insert_message(userMes, messages)
            elif command_request == "createKey":
                
                user = (await reader.read(512)).decode("utf-8") #user
                if await checkforquit(user, writer):
                    return 
                writer.write(RESPONSE_VALUE)
                writer.drain()
                status = benutzer.createKey(user).encode("utf-8")+"\n".encode("utf-8")
                if benutzer.status == 1:
                    writer.write("1\n".encode("utf-8"))
                    await writer.drain()
                    continue
                elif benutzer.status == 0:
                    writer.write(status)
                    await writer.drain()
                    P = (await reader.read(512)).decode(encoding="utf-8")
                    if checkforquit(P, writer):
                        return
                    writer.write(RESPONSE_VALUE)
                    await writer.drain()
                    G = (await reader.read(512)).decode(encoding="utf-8")
                    if checkforquit(G, writer):
                        return
                    writer.write(RESPONSE_VALUE)
                    await writer.drain()
                    A = (await reader.read(512)).decode(encoding="utf-8")
                    if checkforquit(A, writer):
                        return
                    writer.write(RESPONSE_VALUE)
                    await writer.drain()
                    benutzer.insert_keys(user, P, G, A)
                    
                elif benutzer.status == 2:
                    writer.write(status)
                    await writer.drain()
                    
                    P,G,A = benutzer.get_keys(user)
                    writer.write((P+"\n").encode("utf-8"))
                    await writer.drain()
                    
                    writer.write((G+"\n").encode("utf-8"))
                    await writer.drain()
                    
                    writer.write((A+"\n").encode("utf-8"))
                    await writer.drain()
                    
                    B = (await reader.read(512)).decode(encoding="utf-8")
                    if checkforquit(B, writer):
                        return
                    
                    writer.write(RESPONSE_VALUE)
                    await writer.drain()
                    benutzer.insert_keys(user=user, aorb = B)
                elif benutzer.status == 3:
                    
                    writer.write(status)
                    await writer.drain()
                    P, B = benutzer.get_keys(user)
                    writer.write((P+"\n").encode("utf-8"))
                    await writer.drain()
                    writer.write((B+"\n").encode("utf-8"))
                    await writer.drain()
                
                    

    except (ConnectionResetError, BrokenPipeError) as E:
        
        writer.close()


 
async def main():
    server = await asyncio.start_server(Client, '0.0.0.0', PORT)
    async with server:
        await server.serve_forever()
if __name__ == '__main__':
    asyncio.run(main())
