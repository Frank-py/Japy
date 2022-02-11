import React from 'react'
import * as FaIcons from "react-icons/fa"
import * as IoIcons from "react-icons/io"

export const Sidebar = [
    {
        title: "Chat",
        path: "/messenger/Chat",
        icon: <FaIcons.FaEnvelopeOpenText />,
        cName: "nav-text"
    },
    {
        title: "Adduser",
        path: "/messenger/Adduser",
        icon: <IoIcons.IoMdPeople />,
        cName: "nav-text"
    },
    {
        title: "Logout",
        path: "/messenger/logout",
        icon: <IoIcons.IoMdPeople />,
        cName: "nav-text"
    }

]