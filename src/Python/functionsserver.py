from sqlite3 import Cursor
from turtle import up
import mysql.connector

from mysql.connector import Error


def create_connection(host_name, user_name, user_password, db_name):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            user=user_name,
            passwd=user_password,
            database=db_name
        )
        print("Connection to MySQL DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")
    return connection
def create_database(connection, query):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        print("Database created successfully")
    except Error as e:
        print(f"The error '{e}' occurred")
def login(connection, user):
    cursor = connection.cursor()
    try:
        cursor.execute('SELECT Password from Persons where Username = "%s";' % (user))
        myresult = cursor.fetchone()[0]
        return myresult
    except Error as e:
        print(e)
def register(connection, user, password, ip):
    cursor = connection.cursor()
    try:
        sql = 'INSERT INTO Persons (Username, Password, IP) VALUES (%s, %s, %s);'
        val = (user, password, ip)
        cursor.execute(sql, val)
        connection.commit()
    except Error as e:
        print(e)
def updateip(connection, user, ip):
    cursor = connection.cursor()
    try:
        cursor.execute("UPDATE Persons SET IP = '%s' WHERE Username = '%s';" % (ip, user))
        connection.commit()
    except Error as e:
        print(e)
def insertmessage(connection, sender, receiver, message):
    cursor = connection.cursor()
    try:
        sql = "INSERT INTO Messages (send, recv, message) VALUES (%s, %s, %s);"
        val = (sender, receiver, message)
        cursor.execute(sql, val)
        connection.commit()
    except:
        print("error")
def checkformessages(connection, receiver):
    cursor = connection.cursor()
    try:
        cursor.execute('SELECT message FROM Messages WHERE recv = "%s"' % receiver)
        nachrichten = cursor.fetchall()
        listen = []
        for i in nachrichten:
            listen.append(i[0])
        return listen
    except:
        print("error")        
        
connection = create_connection("localhost", "daniel", "4sdf38§$/WE3/FW§459fd2w3", "Messenger")
