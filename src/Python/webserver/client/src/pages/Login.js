import React, {useState, useEffect} from 'react';
import { render } from 'react-dom';
import "./Login.css"
let loggedin = null;
function Login() {
  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  const [WrongPassword, setWrongPassword] = useState('');
  useEffect(() => {
    return <p>WrongPassword</p>,
    [WrongPassword]});
  const handleUserChange = event => {
      setUser(event.target.value)
  }
  const handlePasswordChange = event => {
      setPassword(event.target.value)
  }
  const handleFormSubmit = (event) => {
    event.preventDefault();
    fetch("/login", {
      method: "POST",
      body: JSON.stringify({
        content: [user, password]
      }),
      headers: {
        "Content-Type": "application/json; charset=UTF-8"
      }
    }).then(response => response.json())
    .then(message => {
      console.log(message);
      if (message.response === "201" || message.response === "202"){
        loggedin = true;
        window.location.href = "/messenger"
      }
      else{
        setWrongPassword("Wrong Password")
      }
    })
  }
  return (<div className="eingabe">
              <form method="post" onSubmit={handleFormSubmit}>
                <p className="password">{WrongPassword}</p>
                <input name="username" placeholder='Username' onChange={handleUserChange}></input>
                <p></p>
                <input type="password" placeholder='Password' name="password" onChange={handlePasswordChange}></input>
                <p></p>
                <input type="submit" value="submit" className="button"></input>
              </form>
    </div>)
}

export default Login;