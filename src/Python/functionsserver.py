from sqlite3 import Cursor
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
def login(connection, user, password, ip):
    cursor = connection.cursor()
    try:
        cursor.execute('SELECT * from Persons where Username = "Quint";')
        myresult = cursor.fetchall()
        return myresult
    except:
        print("Error")
def register(connection, user, password, ip):
    cursor = connection.cursor()
    try:
        sql = 'INSERT INTO Persons (Username, Password, IP) VALUES (%s, %s, %s);'
        val = (user, password, ip)
        cursor.execute(sql, val)
    except Error as e:
        print(e)
def updateip(connection, user, ip):
    cursor = connection.cursor()
    try:
        sql = "UPDATE Persons SET IP = '%s' WHERE user = '%s'"
        cursor.execute(sql, (user, ip))
        connection.commit()
    except:
        print("error")
connection = create_connection("ip", "username", "passwort", "database")

