#The code uses a number of hard-coded values, such as the port number and the buffer size. These values should be moved to constants at the top of the file so that they can be easily changed if needed.
#The code is quite long and complex, with a large number of nested if statements and nested while loops. This can make the code difficult to read and understand. It may be helpful to refactor the code to break it up into smaller, more modular functions that each perform a specific task. This can make the code more readable and easier to maintain.
#The code uses a number of "magic numbers" (i.e., numerical values that are used without explanation or context). For example, the code uses the number "2" to specify the number of times to read the user's username and password, and the number "5000" to specify the maximum size of a message. It would be more readable and maintainable to replace these magic numbers with constants that are given descriptive names.
#The code uses the md5 hash function to hash the user's password before checking if it is valid. However, md5 is considered to be a weak and insecure hash function and should not be used for password hashing. It would be more secure to use a stronger and more modern hash function, such as sha256 or bcrypt.
#The code does not include any error handling or validation. For example, if the client sends a command that is not recognized by the server, the code will simply ignore it. This can lead to unexpected behavior and make it difficult to diagnose problems. It would be a good idea to add some error handling and validation to the code to ensure that it can gracefully handle invalid input and unexpected situations.
#import bcrypt
#
#password = "my_password"
#
## Hash the password using bcrypt with a "work factor" of 12
#hashed_password = bcrypt.hashpw(password.encode("utf-8"), bcrypt.gensalt(12))

# Check if the password matches the hashed password
#if bcrypt.checkpw(password.encode("utf-8"), hashed_password):
#    # The password is correct
#else:
#    # The password is incorrect
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
                    benutzer.fetch_friends()
                    break
                elif benutzer.loggedin:
                    writer.write("1\n".encode('utf-8'))
                    await writer.drain()
                    break
                else:
                    writer.write("2\n".encode('utf-8')) 
                    await writer.drain()
        while True: 
            benutzer.checkformessages()
            
            command_request = (await reader.read(512)).decode("utf-8")
            writer.write("200\n".encode("utf-8"))
            await writer.drain()
            if await checkforquit(response, writer):
                return
            
           
            if command_request == "getMes":
                
                user = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return
                messages = benutzer.checkformessages(user)
                
                writer.write((messages+"\n").encode("utf-8"))
                await writer.drain()
                continue
            elif command_request == "proofuser":
                
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
                
                userMes = (await reader.read(512)).decode("utf-8")
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(user, writer):
                    return
            
                messages = (await reader.read(5000)).decode("utf-8")
                
                writer.write("200\n".encode("utf-8"))
                await writer.drain()
                if await checkforquit(messages, writer):
                    return
                benutzer.insertmessage(userMes, messages) 
            elif command_request == "createKey":
                
                user = (await reader.read(512)).decode("utf-8") #user
                
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
                    
                elif benutzer.status == 2:
                    writer.write(status)
                    await writer.drain()
                    
                    P,G,A = benutzer.getKeys(user)
                    writer.write((P+"\n").encode("utf-8"))
                    await writer.drain()
                    
                    writer.write((G+"\n").encode("utf-8"))
                    await writer.drain()
                    
                    writer.write((A+"\n").encode("utf-8"))
                    await writer.drain()
                    
                    B = (await reader.read(512)).decode(encoding="utf-8")
                    
                    writer.write("200\n".encode("utf-8"))
                    await writer.drain()
                    benutzer.insertKeys(user=user, aorb = B)
                elif benutzer.status == 3:
                    
                    writer.write(status)
                    await writer.drain()
                    P, B = benutzer.getKeys(user)
                    writer.write((P+"\n").encode("utf-8"))
                    await writer.drain()
                    writer.write((B+"\n").encode("utf-8"))
                    await writer.drain()
                
                    

    except (ConnectionResetError, BrokenPipeError) as E:
        
        writer.close()
            
async def main():
    server = await asyncio.start_server(Client, 'localhost', PORT)
    async with server:
        await server.serve_forever()
if __name__ == '__main__':
    asyncio.run(main())
