from time import localtime, strftime
import bcrypt
import mysql.connector
from mysql.connector import Error


def create_connection():
    connection = None
    try:
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            # user="***REMOVED***",
            # passwd="***REMOVED***",
            passwd="",
            database="Messenger"
        )
    except Error as e:
        print(f"The error '{e}' occurred")
    return connection


class User():
    def __init__(self, user=None, date=None, logged_in=False, registered=False):
        self.user = user
        self.status = 0
        self.connection = create_connection()
        self.logged_in = logged_in
        self.registered = registered

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
            cursor.execute(
                'SELECT Password from People where Username = "%s";' % (user))
            stored_password = cursor.fetchone()[0]
            if bcrypt.checkpw(password.encode("utf-8"), stored_password.encode("utf-8")):
                self.user = user
                self.logged_in = True
                self.registered = False
            else:
                self.logged_in = False
                self.registered = False
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
            self.logged_in = True
            self.registered = True
        except Error as e:
            print(e)

    def fetch_friends(self):
        cursor = self.connection.cursor()
        try:
            cursor.execute(
                'SELECT receive from Messages where send = "%s")', self.user)
            self.friends = cursor.fetchall()
            cursor.execute(
                'SELECT send from Messages where receive = "%s")', self.user)
            self.friends = set(cursor.fetchall[0], self.friends)
        except:
            print("idk man")

    def insert_message(self, receive, text):
        cursor = self.connection.cursor()
        try:
            sql = "INSERT INTO Messages (send, receive, Message, Time) VALUES (%s, %s, %s, %s);"
            now = str(strftime("%Y-%m-%d %H:%M:%S", localtime())).strip("'")
            val = (self.user, receive, text, now)
            cursor.execute(sql, val)
            self.connection.commit()
        except:
            print("error")

    # def checkformessages(self, receive = None):
    #     cursor = self.connection.cursor()
    #     messages = []
    #     try:
    #         if receive != None:
    #             cursor.execute('SELECT Time, Message FROM Messages WHERE (receive = "%s");' % (self.user))
    #             messages = cursor.fetchall()
    #         else:
    #             cursor.execute('SELECT Time, Message FROM Messages WHERE (receive = "%s" and send "%s");' % (self.user, receive))
    #             nachrichten_empfangen = cursor.fetchall()
    #             messages.append(nachrichten_empfangen)
    #             cursor.execute('SELECT Time, Message FROM Messages WHERE (receive = "%s" and send "%s");' % (receive, self.user))
    #             nachrichten_gesendet = cursor.fetchall()
    #             messages.append(nachrichten_gesendet)
    #         if len(messages) == 0:
    #             return ""
    #         else:
    #             return ";;;".join(messages)

    #     except:
    #         return "error"
    def check_for_messages(self, receive=None, start=0, end=5):
        cursor = self.connection.cursor()
        messages = []
        try:
            if receive is not None:
                cursor.execute(
                    'SELECT Message FROM Messages WHERE receive = "%s" ORDER BY Time DESC LIMIT %s OFFSET %s;' % (
                        self.user, end - start + 1, start - 1))
                messages = cursor.fetchall()
            else:
                cursor.execute(
                    'SELECT Message FROM Messages WHERE (receive = "%s" and send "%s") '
                    'ORDER BY Time DESC LIMIT %s '  # FIXME inconsistent '
                    'OFFSET %s;' % (
                        self.user, receive, end - start + 1, start - 1))
                received_messages = cursor.fetchall()
                messages.append(received_messages)
                cursor.execute(
                    'SELECT Message FROM Messages WHERE (receive = "%s" and send "%s") '
                    'ORDER BY Time DESC LIMIT %s OFFSET %s;' % (
                        receive, self.user, end - start + 1, start - 1))
                send_messages = cursor.fetchall()
                messages.append(send_messages)
            if len(messages) == 0:
                return ""
            else:
                return ";;;".join(messages)
                # return json.dumps(messages)

        except:
            return "error"

    # def check_for_messages(self, receive=None, offset=1):
    #     cursor = self.connection.cursor()
    #     messages = []
    #     try:
    #         if receive is not None:
    #             executed_cmd = 'SELECT Time, Message, send, receive FROM Messages WHERE ((receive = "{}" and send = "{}") or (receive = "{}" and send = "{}") and displayed = false) ORDER BY Time DESC LIMIT 5 OFFSET 5*{};'.format(self.user, receive, receive, self.user, offset)
    #             cursor.execute(executed_cmd)
    #             rows = cursor.fetchall()
    #             for row in rows:
    #                 messages.append({
    #                     "message": row[1],
    #                     "send": row[2],
    #                     "receive": row[3],
    #                     "time": row[0],
    #                 })
    #             cursor.execute('UPDATE Messages SET displayed = true WHERE (receive = "%s" and displayed = false);' % (self.user))
    #         else:
    #             executed_cmd = 'SELECT Time, Message, send, receive FROM Messages WHERE (receive = "{}" and displayed = false) ORDER BY Time DESC LIMIT 5 OFFSET 5*{};'.format(self.user, offset)
    #             cursor.execute(executed_cmd)
    #             rows = cursor.fetchall()
    #             for row in rows:
    #                 messages.append({
    #                     "message": row[1],
    #                     "send": row[2],
    #                     "receive": row[3],
    #                     "time": row[0],
    #                 })
    #             cursor.execute('UPDATE Messages SET displayed = true WHERE (receive = "%s" and send = "%s" and displayed = false);' % (self.user, receive))

    #         if len(messages) == 0:
    #             return ""
    #         else:
    #             return json.dumps(messages)

    #     except:
    #         return "error"   
    # def checkformessages(self, receive = None, offset = 1):
    # cursor = self.connection.cursor()
    # messages = []
    # try:
    #    if receive != None:
    #        executed_cmd = 'SELECT Time, Message FROM Messages WHERE (receive = "{}" and displayed = false) ORDER BY Time DESC LIMIT 5 OFFSET 5*{};'.format(self.user, receive, offset)
    #        cursor.execute(executed_cmd)
    #        messages = cursor.fetchall()
    #        cursor.execute('UPDATE Messages SET displayed = true WHERE (receive = "%s" and displayed = false);' % (self.user))
    #    else:
    #            executed_cmd = 'SELECT Time, Message FROM Messages WHERE (receive = "{}" and send "{}" and displayed = false) ORDER BY Time DESC LIMIT 5 OFFSET 5*{};'.format(self.user, receive, offset)
    #        cursor.execute(executed_cmd)
    #        nachrichten_empfangen = cursor.fetchall()
    #            executed_cmd = 'SELECT Time, Message FROM Messages WHERE (receive = "{}" and send "{}" and displayed = false) ORDER BY Time DESC LIMIT 5 OFFSET 5*{};'.format(receive, self.user, offset)
    #        cursor.execute(executed_cmd)
    #        nachrichten_gesendet = cursor.fetchall() 
    #        messages.append(nachrichten_gesendet)
    #        cursor.execute('UPDATE Messages SET displayed = true WHERE (receive = "%s" and send "%s" and displayed = false) ;' % (self.user, receive))
    #        if len(messages) == 0:
    #            return ""
    #        else:
    #            return ";;;".join(messages)

    #    except:
    #        return "error"
    def check_account(self, name):
        cursor = self.connection.cursor()
        try:
            cursor.execute(
                'SELECT * FROM People WHERE Username = "%s";' % (name))
            messages = cursor.fetchall()
            if messages:
                return True  # login
            else:
                return False  # register
        except Exception as e:
            return e

    def search_account(self, user):
        cursor = self.connection.cursor()
        try:
            cursor.execute(
                'SELECT 1 FROM People WHERE Username = "%s";' % (user))
            messages = cursor.fetchall()
            return len(messages) != 0
        except Exception as e:
            return e

    def get_keys(self, user):
        cursor = self.connection.cursor()
        try:
            if self.status == 2:
                cursor.execute(
                    'SELECT P, G, A FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (user, self.user))
                messages = cursor.fetchall()
                return map(str, tuple(messages[0]))
            elif self.status == 3:
                cursor.execute(
                    'SELECT P, B FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (self.user, user))
                messages = cursor.fetchall()
                return map(str, tuple(messages[0]))

        except Exception as e:
            return e

    def createKey(self, user2):
        cursor = self.connection.cursor()
        try:
            self.user1binich = True
            cursor.execute('SELECT 1 FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (
                self.user, user2))
            nachrichten = cursor.fetchall()
            if len(nachrichten) == 0:
                self.user1binich = False
                cursor.execute('SELECT 1 FROM KeyCache WHERE user1 = "%s" AND user2 = "%s";' % (
                    user2, self.user))
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
                cursor.execute('SELECT 1 FROM KeyCache WHERE user1 = "%s" AND user2 = "%s" AND B IS NULL;' % (
                    self.user, user2))
                nachrichten = cursor.fetchall()
                if len(nachrichten) == 0:
                    self.status = 3
                    return "3"
                self.status = 1
                return "1"

        except Exception as e:
            return e

    def insert_keys(self, user, P=None, G=None, aorb=None):
        cursor = self.connection.cursor()
        try:
            if self.status == 0 and (P, G) is not None:
                print("wth")
                # ich bin A also insert ich alle values
                sql = "INSERT INTO KeyCache (user1, user2, p, g, A) VALUES (%s, %s, %s, %s, %s);"
                val = (self.user, user, P, G, aorb)
                cursor.execute(sql, val)
                self.connection.commit()
                return
            elif self.status == 2:
                print("this should be right, right?")
                sql = "UPDATE KeyCache SET B = %s WHERE user1 = %s AND user2 = %s AND p is not NULL;"
                print(aorb, user, self.user)
                val = (aorb, user, self.user)
            cursor.execute(sql, val)
            self.connection.commit()
        except Exception as E:
            print(E)
