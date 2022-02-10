from flask import Flask, request, redirect, json
import os, sys, random
sys.path.insert(1, os.path.join(sys.path[0], '../..'))
from functionsserver import *
Benutzer = User()
app = Flask(__name__)

@app.route("/login", methods=["POST"])
def login():
    request_method = request.method
    if request_method == "POST":
        form = json.loads(request.data)
        print(form)
        benutzer = User().checkaccount(form["content"][0], hashlib.md5(bytes(form['content'][1], encoding='UTF-8')).hexdigest(), "123")
        if benutzer.loggedin and benutzer.registriert:
            #redirect!
            return {"response": "202"}
        elif benutzer.loggedin:
            return {"response": "201"}
        else:
            return {"response": "401"}
@app.route("/login", methods=["POST", "GET"])
if __name__ == "__main__":
    app.run(debug=True)