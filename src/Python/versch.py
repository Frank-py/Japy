from PyQt5.QtWidgets import (QApplication, QWidget, QPushButton, 
QLineEdit, QInputDialog)
def Binär(text):
    l, m = [], []
    for i in text:
        l.append(ord(i))
    for i in l:
        m.append(str(bin(i)[2:]))
    for i in range(len(m)):
        while len(m[i]) % 8 != 0:
            m[i] = "0" + m[i]
    var = "".join(m)
    return var
def Binärrück(liste):
    l = []
    liste1 = []
    for index in range(0, len(liste), 8):
        liste1.append(liste[index:index + 8])
    for i in liste1:
        l.append(chr(int(i, 2)))
    return "".join(l)
def Schluessel(B, a, P):
    return pow(B, a, P)
def versch(Nachricht, key):
    key = str(bin(int(key)))[2:]
    if isinstance(Nachricht, list):
        Nachricht = "".join([i for i in Nachricht])
    Nachricht = str(Nachricht)
    if len(Nachricht) > len(key):
        while len(Nachricht) > len(key):
            key *= 2
    
    key = list(key)
    Nachricht = list(Nachricht)
    stra = ""
    for i in range(len(Nachricht)):
        if Nachricht[i] == key[i]:
            stra += "0"
        else:
            stra += "1"
    return "".join(stra)
def Verschluesseln(Nachricht, key):
    return versch(Binär(Nachricht), str(key))

def Entschluesseln(Nachricht, key):
    return Binärrück(versch(Nachricht, str(key)))
