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
        
def query(connection, Table):
    cursor = connection.cursor()
    try:
        sql = 'INSERT INTO %s (Username, Password, IP) VALUES (%s, %s, %s);'
        val = (Table[0], Table[1], Table[2], Table[3])
        cursor.execute(sql, val)
        connection.commit()# Wenn verändert werden muss
    except Error as e:
        print(e)
def show(connection, spalte):
    cursor = connection.cursor()
    try:
        sql = 'SELECT %s (Username, Password, IP) VALUES (%s, %s, %s);'
        #'SELECT IP from Persons where Username = "Quint";'
        val = (spalte[0], spalte[1], spalte[2], spalte[3])
        cursor.execute(sql, val)
        #myresult = cursor.fetchone()/cursor.fetchall()# wenn anzeigen
    except Error as e:
        print(e)