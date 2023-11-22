from flask import Flask, request, redirect, json
import os, sys, random
import hashlib
sys.path.insert(1, os.path.join(sys.path[0], '../..'))
from functionsserver import *
benutzer = None
app = Flask(__name__)

@app.route("/login", methods=["POST"])
def login():
    global benutzer
    request_method = request.method
    if request_method == "POST":
        form = json.loads(request.data)
        benutzer = User().check_account(form["content"][0], hashlib.md5(bytes(form["content"][1], encoding='utf-8')).hexdigest())
        if benutzer.logged_in and benutzer.registered:
            #redirect!
            return {"response": "202"}
        elif benutzer.logged_in:
            return {"response": "201"}
        else:
            return {"response": "401"}
@app.route("/messenger", methods=["POST", "GET"])
def messenger():
    request_method = request.method
    if request_method == "POST":
        form = json.loads(request.data)
        if form["content"][0] == "checkaccount":
            if (benutzer.checkaccount(form["content"][1])):
                return {"response": "201"}
            else:
                return {"response": "401"}
    if request_method == "GET":
        if benutzer != None:
            return {"response": "403"}
        else:
            return {"response": "201"}
if __name__ == "__main__":
    app.run(debug=True)
