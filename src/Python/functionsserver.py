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
def query(connection, query):
    cursor = connection.cursor()
    try:
        # sql = 'INSERT INTO Persons (Username, Password, IP) VALUES (%s, %s, %s);'
        # val = (query[0], query[1], query[2])
        # cursor.execute(sql, val)
        cursor.execute('SELECT IP from Persons where Username = "Quint";')
        myresult = cursor.fetchone()# wenn anzeigen
        #connection.commit()# Wenn verändert werden muss
        return myresult[0]
    except Error as e:
        print(e) #Nur wenn angezeigt werden muss
connection = create_connection("ip", "username", "passwort", "database")
a = query(connection, ["daniel", "asdasd", "0.0.0.0"])
print(a)
#create_database_query = "CREATE DATABASE Messenger"
#create_database(connection, create_database_query, "Messenger")

