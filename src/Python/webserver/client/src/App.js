import React, {useState, useEffect} from 'react';
import "./style.css"
function App() {
  const [data, setData] = useState([{}]);
  useEffect(() => {
    fetch("/login").then(
      res => res.json()
      ).then(
        data => {
        setData(data)
        console.log(data)
      }
    )
  }, [])
  return (<div className="eingabe">
    <p><input placeholder='Username'></input></p>
    <input placeholder='Password'></input>
    <p><button className="button">{data["loggedin"]}</button></p>
    </div>)
}

export default App;
