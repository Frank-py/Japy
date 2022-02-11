import React, {useState} from 'react';
import * as FaIcons from "react-icons/fa"
import * as AiIcons from "react-icons/ai"
import { Link } from "react-router-dom";
import {Sidebar} from "./SidebarData"
import styles from "./Navbar.module.scss"
import { IconContext } from "react-icons"
function Navbar() {
    const [sidebar, setSidebar] = useState(false);

    const showSidebar = () => {
        setSidebar(!sidebar);
    }
    return (
        <IconContext.Provider value={{color: "#fff"}}>
        <div className={styles.navbar}>
            <Link to="#" className={styles["menu-bars"]}>
                <  FaIcons.FaBars onClick={showSidebar}/>
            </Link>
        </div>
        <nav className={styles.sidebar ? styles["nav-menu active"] : styles["nav-menu"]}>
        <ul className={styles["nav-menu-items"]} onClick={showSidebar}>
            <li classname={styles["navbar.toggle"]}>
              <Link to="#" className={styles["menu-bars"]}>
                  <AiIcons.AiOutlineClose />
              </Link>  
            </li>
            {Sidebar.map((item, index) => {
                return (
                    <li key={index} className={styles[item.className]}>
                        <Link to={item.path}>
                            {item.icon}
                            <span>{item.title}</span>
                        </Link>
                    </li>
                )
            })}
        </ul>

    </nav>
    </IconContext.Provider>

    )
}

export default Navbar;