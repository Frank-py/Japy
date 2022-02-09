from atexit import register
import mysql.connector
from mysql.connector import Error

import socket, threading, hashlib

class User():
    def __init__(self, user=None, password=None, ip=None, loggedin=None, registriert=False):
        self.user = user
        self.password = password
        self.ip = ip
        self.connection = self.create_connection()
        self.loggedin = loggedin
        self.registriert = registriert
    def create_connection(self):
        connection = None
        try:
            connection = mysql.connector.connect(
                 host="localhost",
                  user="***REMOVED***",
                  passwd="***REMOVED***",
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
    @classmethod
    def login(cls, connection, user, password, ip):
        cursor = connection.cursor()
        try:
            cursor.execute('SELECT Password from Persons where Username = "%s";' % (user))
            myresult = cursor.fetchone()[0]
            if myresult == password:
                return cls(user, password, ip, True, False)
            else:
                return cls(None, None, None, False, False)
        except Error as e:
            print(e)
    @classmethod
    def register(cls, connection, username, password, ip):
        cursor = connection.cursor()
        try:
            sql = 'INSERT INTO Persons (Username, Password, IP) VALUES (%s, %s, %s);'
            val = (username, password, ip)
            cursor.execute(sql, val)
            connection.commit()
            return cls(username, password, ip, True, True)
        except Error as e:
            return "error"
    def updateip(self, connection, username, ip):
        cursor = self.connection.cursor()
        try:
            cursor.execute("UPDATE Persons SET IP = '%s' WHERE Username = '%s';" % (ip, username))
            connection.commit()
            self.ip = ip
        except Error as e:
            print(e)
    def insertmessage(self, liste):
        cursor = self.connection.cursor()
        try:
            sql = "INSERT INTO Messages (send, recv, message) VALUES (%s, %s, %s);"
            val = (self.user, self.recv, " ".join(liste[2:]).strip())
            cursor.execute(sql, val)
            self.connection.commit()
        except:
            print("error")
    def checkformessages(self, receiver):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT message FROM Messages WHERE recv = "%s" AND send = "%s";' % (receiver, self.user))
            nachrichten = cursor.fetchall()
            listen = []
            for i in nachrichten:
                listen.append(i[0])
            return listen
        except:
            return "error"
    def checkaccount(self, name, password, ip):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT * FROM Persons WHERE Username = "%s";' % (name))
            nachrichten = cursor.fetchall()
            try:
                print(nachrichten[0][0])
                if name == nachrichten[0][0]:
                    return self.login(self.connection, name, password, ip)
            except:    
                return self.register(self.connection, name, password, ip)
        except Exception as e:
            return e
    def searchaccount(self, name):
        cursor = self.connection.cursor()
        try:
            cursor.execute('SELECT IP FROM Persons WHERE Username = "%s";' % (name))
            nachrichten = cursor.fetchall()
            try:
                print(nachrichten[0][0])
                if name == nachrichten[0][0]:
                    return self.login(self.connection, name, password, ip)
            except:    
                return self.register(self.connection, name, password, ip)
        except Exception as e:
            return e