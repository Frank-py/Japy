from PyQt5.QtWidgets import (QApplication, QWidget, QPushButton, 
QLineEdit, QInputDialog)
from PyQt5.QtWidgets import QApplication, QWidget, QPushButton, QMessageBox
from PyQt5.QtGui import QIcon
from PyQt5.QtCore import pyqtSlot
from versch import *
from random import randint
import sympy, sys, webbrowser, os, numpy as np

class Example(QWidget):
    def __init__(self):
        super().__init__()
        self.le = QLineEdit(self)
        self.text = self.le.text()
        self.left = 800
        self.top = 300
        self.width = 320
        self.height = 200
    
    def showDialog(self, Nachricht):
        self.setWindowTitle("test")
        self.setGeometry(self.left, self.top, self.width, self.height)
        text, ok = QInputDialog.getText(self, 'Verschlüsseln', Nachricht)
        if ok:
            return text
    def initUI(self, Nachricht):
        self.setWindowTitle("test")
        self.setGeometry(self.left, self.top, self.width, self.height)

        buttonReply = QMessageBox.question(self, 'Nachricht', Nachricht, QMessageBox.Yes | QMessageBox.No, QMessageBox.No)
        if buttonReply == QMessageBox.Yes:
            return
        else:
            sys.exit()
app = QApplication(sys.argv)
ex = Example()

Frage = ex.showDialog("Key generieren?")
if Frage.lower().startswith("j"):
    P = ex.showDialog("Geben sie eine Primzahl für P ein oder R für eine züfallig gewählte Primzahl:")
    G = ex.showDialog("Geben sie eine Zahl für G ein oder R für eine züfallig gewählte Zahl:")
    if P.lower().startswith("r"):
        P = sympy.randprime(1, 100000)
    if G.lower().startswith("r"):
        G = randint(1, 100000)
    P, G = int(P), int(G)
    a = randint(1, P-1)
    A = Schluessel(G, a, P)
    
    ex.initUI(f"Der Schlüssel ist {A}\nP ist {P} und G ist {G}")
    B = int(ex.showDialog("Geben sie die Zahl des Senders ein:"))   
    Key = Schluessel(B, a, P)
    ex.initUI(f"Der Schlüssel zum Verschlüsseln und Entschlüsseln ist {Key}")
    while True:
        jn = ex.showDialog("Ver- oder Entschlüsseln?(Ver und Ent reicht):")
        if jn.lower().startswith("ver"):       
            Nachricht = ex.showDialog("Geben Sie die Nachricht ein, die sie verschlüsseln wollen:")
            ex.initUI(f"Die Verschlüsselte Nachricht ist {Verschluesseln(Nachricht, Key)}")
        else:       
            Nachricht = ex.showDialog("Geben Sie die Nachricht ein, die sie entschlüsseln wollen:")
            ex.initUI(f"Die entschlüsselte Nachricht ist {Entschluesseln(Nachricht, Key)}")
else:
    Key = int(ex.showDialog("Geben Sie den Key ein"))
    while True:       
        jn = ex.showDialog("Ver- oder Entschlüsseln?(Ver und Ent reicht):")
        if jn.lower().startswith("ver"):       
            Nachricht = ex.showDialog("Geben Sie die Nachricht ein, die sie verschlüsseln wollen:")
            ex.initUI(f"Die Verschlüsselte Nachricht ist {Verschluesseln(Nachricht, Key)}")
        else:       
            Nachricht = ex.showDialog("Geben Sie die Nachricht ein, die sie entschlüsseln wollen:")
            ex.initUI(f"Die entschlüsselte Nachricht ist {Entschluesseln(Nachricht, Key)}")