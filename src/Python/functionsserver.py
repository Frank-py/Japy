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
           # user="admin",
           # passwd="4sdf38§$/WE3/FW§459fd2w3",
   
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
    def getKeys(self, user):
        cursor = self.connection.cursor()
        try:
            if self.status == 2:
                cursor.execute('SELECT P, G, A FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (user, self.user))
                nachrichten = cursor.fetchall()
                return map(str, tuple(nachrichten[0]))
            elif self.status == 3:
                cursor.execute('SELECT P, B FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (self.user, user))
                nachrichten = cursor.fetchall()
                return map(str, tuple(nachrichten[0]))
                
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
                else:
                    self.user1binich = False
                    self.status = 2
                    return "2"
            else:
                cursor.execute('SELECT 1 FROM KeyCache WHERE user1 = "%s" AND user2 = "%s" AND B IS NULL;' % (self.user, user2))
                nachrichten = cursor.fetchall()
                if len(nachrichten) == 0:
                    self.status = 3
                    return "3"
                self.status = 1
                return "1"
                
        except Exception as e:
            return e
    def insertKeys(self, user, P=None, G=None, aorb=None):
        cursor = self.connection.cursor()        
        try:
            if self.status == 0 and (P, G) is not None:
                sql = "INSERT INTO KeyCache (user1, user2, p, g, A) VALUES (%s, %s, %s, %s, %s);" # ich bin A also inserte ich alle values
                val = (self.user, user, P, G, aorb)
                cursor.execute(sql, val)
                self.connection.commit()
                return
            elif self.status == 2:
                sql = "UPDATE KeyCache SET B = %s WHERE user1 = %s AND user2 = %s AND p is not NULL;"
                val = (aorb, user, self.user)
            cursor.execute(sql, val)
            self.connection.commit()
        except Exception as E:
            print("An erro occured!")
        


