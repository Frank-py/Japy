from atexit import register
import mysql.connector
from mysql.connector import Error
from time import localtime, strftime
import versch, bcrypt
class User():
    def __init__(self, user=None, date=None, loggedin=False, registriert=False):
        self.user = user
        self.status = 0 
        self.connection = self.create_connection()
        self.loggedin = loggedin
        self.registriert = registriert
    def create_connection(self):
        connection = None
        try:
            connection = mysql.connector.connect(
                 host="localhost",
           #   user="***REMOVED***",
              user="***REMOVED***",
              passwd="***REMOVED***",
   
  
  #            passwd="***REMOVED***",
                  database="***REMOVED***"
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
    def login(self, user, password):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT Password from People where Username = "%s";' % (user))
            stored_password = cursor.fetchone()[0]
            if bcrypt.checkpw(password.encode("utf-8"), stored_password.encode("utf-8")):
                self.user = user
                self.loggedin = True
                self.registriert = False
            else:
                self.loggedin = False
                self.registriert = False
        except Error as e:
            print(e)
    def register(self, username, password):
        cursor = self.connection.cursor()
        try:
            now = str(strftime("%Y-%m-%d %H:%M:%S", localtime())).strip("'")
            sql = "INSERT INTO People (Username, Password, CD) VALUES (%s, %s, %s);"
            val = (username, password, now)
            cursor.execute(sql, val)
            self.connection.commit()
            self.user = username
            self.loggedin = True
            self.registriert = True
        except Error as e:
            print(e)
    def fetch_friends(self):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT recv from Messages where send = "%s")', (self.user))
            self.friends = cursor.fetchall()
            cursor.execute('SELECT send from Messages where recv = "%s")', (self.user))
            self.friends = set(cursor.fetchall[0], self.friends)  
        except:
            print("idk man")
    def insertmessage(self, recv, text):
        cursor = self.connection.cursor()
        try:
            sql = "INSERT INTO Messages (send, recv, Message, Time) VALUES (%s, %s, %s, %s);"
            now = str(strftime("%Y-%m-%d %H:%M:%S", localtime())).strip("'")
            val = (self.user, recv, text, now)
            cursor.execute(sql, val)
            self.connection.commit()
        except:
            print("error")
    def checkformessages(self, recv = None):
        cursor = self.connection.cursor()
        try:
            if recv != None: 
                cursor.execute('SELECT Time, Message FROM Messages WHERE (recv = "%s" AND send = "%s");' % (self.user, recv))
                nachrichten_empfangen = cursor.fetchall() 
                cursor.execute('SELECT Time, Message FROM Messages WHERE (recv = "%s" AND send = "%s");' % (recv, self.user))
                nachrichten_gesendet = cursor.fetchall()
                return str(";;;".join([i[0] for i in nachrichten]))
            else:
                returnable_list = []
                cursor.execute('SELECT TIME, send, recv, Message From Messages WHERE (recv = "%s");'  % self.user)
                for i in cursor.fetchall():
                    if i not in self.lastmessages:
                        returnable_list.append(i)
                    else:
                        self.lastmessages.append(i)
                return returnable_list

        except:
            return "error"
    def checkaccount(self, name):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT * FROM People WHERE Username = "%s";' % (name))
            nachrichten = cursor.fetchall()
            if nachrichten:
                return True # login
            else:    
                print("registER=!=!==!=!")
                return False #register
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
                print("wth")
                sql = "INSERT INTO KeyCache (user1, user2, p, g, A) VALUES (%s, %s, %s, %s, %s);" # ich bin A also inserte ich alle values
                val = (self.user, user, P, G, aorb)
                cursor.execute(sql, val)
                self.connection.commit()
                return
            elif self.status == 2:
                print("this shouuld be right, right?")
                sql = "UPDATE KeyCache SET B = %s WHERE user1 = %s AND user2 = %s AND p is not NULL;"
                print(aorb, user, self.user)
                val = (aorb, user, self.user)
            cursor.execute(sql, val)
            self.connection.commit()
        except Exception as E:
            print(E)
        


