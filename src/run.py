import subprocess, os, threading, platform, time
if platform.system() == "Linux":
    # try:
    #     os.system("sudo kill $(ps -aux | grep '[p]ython3' | awk '{print $2}')")
    # except:
    #     print("no process to kill")    
    threading.Thread(target=subprocess.Popen, args=(["python3", "Python/Server.py"],)).start()
    while True:
        time.sleep(1)
        out = subprocess.call(["javac", "Java/run.java"])
        out = subprocess.Popen(["java", "Java.run"])
        out.wait()
        os.system("rm Java/*.class")
else:
    threading.Thread(target=subprocess.Popen, args=(["python", "src\\Java\\servertest.py"],)).start()
    out = os.system("cd \\src\\")
    while True:
        time.sleep(1)
        out = os.system("javac Java\\run.java")
        out = subprocess.Popen(["java", "Java.run"])
        out.wait()
        os.system("del Java\\*.class")