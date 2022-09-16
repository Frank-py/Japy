from atexit import register
import mysql.connector
from mysql.connector import Error
from time import gmtime, strftime
import versch
class User():
    def __init__(self, user=None, password=None, date=None, loggedin=None, registriert=False):
        self.user = user
        self.password = password
        self.status = 0 
        self.date = date
        self.connection = self.create_connection()
        self.loggedin = loggedin
        self.registriert = registriert
    def create_connection(self):
        connection = None
        try:
            connection = mysql.connector.connect(
                host="localhost",
                user="root",
                passwd="",
                database="Messenger"
        )
        except Error as e:
            print(f"The error '{e}' occurred")
        return connection
    def create_database(self, query):
        cursor = self.connection.cursor()
        try:
            cursor.execute(query)
            print("Database created successfully")
        except Error as e:
            print(f"The error '{e}' occurred")
    @classmethod
    def login(cls, connection, user, password):
        cursor = connection.cursor()
        try:
            cursor.execute('SELECT Password from People where Username = "%s";' % (user))
            myresult = cursor.fetchone()[0]
            if myresult == password:
                return cls(user, password, None, True, False)
            else:
                return cls(None, None, None, False, False)
        except Error as e:
            print(e)
    @classmethod
    def register(cls, connection, username, password):
        cursor = connection.cursor()
        try:
            now = str(strftime("%Y-%m-%d %H:%M:%S", gmtime())).strip("'")
            sql = "INSERT INTO People (Username, Password, CD) VALUES (%s, %s, %s);"
            val = (username, password, now)
            cursor.execute(sql, val)
            connection.commit()
            return cls(username, password, now, True, True)
        except Error as e:
            return e
    def insertmessage(self, recv, text):
        cursor = self.connection.cursor()
        try:
            sql = "INSERT INTO Messages (send, recv, Message, Time) VALUES (%s, %s, %s, %s);"
            now = str(strftime("%Y-%m-%d %H:%M:%S", gmtime())).strip("'")
            val = (self.user, recv, text, now)
            cursor.execute(sql, val)
            self.connection.commit()
        except:
            print("error")
    def checkformessages(self, recv):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT message FROM Messages WHERE recv = "%s" AND send = "%s";' % (self.user, recv))
            nachrichten = cursor.fetchall()
            listen = []
            for i in nachrichten:
                listen.append(i[0])
            return str(";".join(listen))
        except:
            return "error"
    def checkaccount(self, name, password):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT * FROM People WHERE Username = "%s";' % (name))
            nachrichten = cursor.fetchall()
            try:
                if name == nachrichten[0][0]:
                    return self.login(self.connection, name, password)
            except:    
                return self.register(self.connection, name, password)
        except Exception as e:
            return e
    def searchaccount(self, user):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT 1 FROM People WHERE Username = "%s";' % (user))
            nachrichten = cursor.fetchall()
            return len(nachrichten) != 0 
        except Exception as e:
            return e
    def createKey(self, user2):
        cursor = self.connection.cursor()        
        try:
            self.user1binich = True
            cursor.execute('SELECT 1 FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (self.user, user2))
            nachrichten = cursor.fetchall()
            if len(nachrichten) == 0:
                self.user1binich = False
                cursor.execute('SELECT 1 FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (user2, self.user))
                nachrichten = cursor.fetchall()
                if len(nachrichten) == 0:
                    self.user1binich = True
                    self.status = 0
                    return "0"
                
            # status es ist nichts in der Datenbank 0
            # status es ist PGA in der Datenbank
            # status es ist PGB in der Datenbank
              
            cursor.execute('SELECT 1 FROM KeyCache WHERE user1 = "%s" AND user2 = "%s" AND A IS NOT NULL;' % (self.user, user2))
            aexistiert = cursor.fetchall()
            if len(aexistiert) != 0:
                if self.user1binich:
                    self.status = 1
                    return "1" # a existiert ich bin a -> nichts machen
                else:
                    self.status = 2
                    return "2" # a existiert ich bin aber nicht a sonderb b, d.h. ich muss a b generieren
                 
            else:
                if not self.user1binich: # B existiert ich bin b also nichts machen
                    self.status = 1
                    return "1"
                else:
                    self.status = 2 #B existiert ich bin A also muss ich mir B snacken und danach löschen
                    return "2"
        except Exception as e:
            return e
    def insertkeys(self, user2, P=None, G=None, a=None):
        cursor = self.connection.cursor()        
        try:
            if self.status == 0:
                sql = "INSERT INTO KeyCache (user1, user2, p, g, A) VALUES (%s, %s, %s, %s, %s);" # ich bin A also inserte ich alle values
                val = (self.user, user2, P, G, a)
                cursor.execute(sql, val)
                self.connection.commit()
                return
            
            if self.status == 2: #

                if self.user1binich:
                    cursor.execute('SELECT p,g,B FROM KeyCache WHERE (user1 = "%s" and user2 = "%s") or (user1 = "%s" and user2 = "%s");' % (self.user, user2, user2, self.user))
                else:
                    
                    nachrichten = cursor.fetchall()
                    print(nachrichten)
                    sql = 'UPDATE KeyCache set B=NULL WHERE (user1 = "%s" AND user2 = "%s") OR (user1 = "%s" AND user2 = "%s");'
                    val = (self.user, user2, user2, self.user)
                    cursor.execute(sql, val)
                    self.connection.commit()
                    return
                else:

                     
                    
                
                
                
        except Exception as E:
            print("An erro occured!")
    def getKeys(self, user2):
        cursor = self.connection.cursor()
        try:
            if self.status == 2:
                cursor.execute('SELECT P, G, A FROM KeyCache WHERE (user1 = "%s" and user2 = "%s") or (user1 = "%s" and user2 = "%s");' % (self.user, user2, user2, self.user))
                nachrichten = cursor.fetchall()
                print(nachrichten)
            elif self.status == 3:
                cursor.execute('SELECT B FROM KeyCache WHERE (user1 = "%s" and user2 = "%s") or (user1 = "%s" and user2 = "%s");' % (self.user, user2, user2, self.user))
                nachrichten = cursor.fetchall()
                print(nachrichten)
                sql = 'UPDATE KeyCache set B=NULL WHERE (user1 = "%s" AND user2 = "%s") OR (user1 = "%s" AND user2 = "%s");'
                val = (self.user, user2, user2, self.user)
                cursor.execute(sql, val)
                self.connection.commit()
        except Exception as E:
            print("An erro occured!")
        


