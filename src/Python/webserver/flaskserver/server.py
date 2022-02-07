from flask import Flask
import os, sys
sys.path.insert(1, os.path.join(sys.path[0], '../..'))
from functionsserver import *
Benutzer = User()
app = Flask(__name__)

@app.route("/login")
def login():
     return {"loggedin": ["yes"]}


if __name__ == "__main__":
    app.run(debug=True)