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
        title: "adduser",
        path: "/messenger/adduser",
        icon: <IoIcons.IoMdPeople />,
        cName: "nav-text"
    }

]