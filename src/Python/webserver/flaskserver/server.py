from flask import Flask
import os, sys, random
sys.path.insert(1, os.path.join(sys.path[0], '../..'))
from functionsserver import *
Benutzer = User()
app = Flask(__name__)

@app.route("/login")
def login():
     if 1 == random.randint(0,10):
          return {"loggedin": ["yes"]}
     else:
          return {"loggedin": ["no"]}

if __name__ == "__main__":
    app.run(debug=True)