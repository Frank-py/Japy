import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Login from "./pages/Login"
import NotFound from "./pages/NotFound"
import Messenger from "./pages/Messenger"
import React from 'react';
import Logout from "./pages/Logout"
const Router = () => {
    document.title = "Messenger";
    return (
        <BrowserRouter>
        <Routes>
            <Route exact path="/login" element={<Login/>}/>
            <Route exact path="/messenger/*" element={<Messenger/>}/>
            <Route path="*" element={<NotFound/>}/>
            <Route path="/" element={<Navigate to="/login"/>}/>
            <Route path="/messenger/logout" element={<Navigate to="/login"/>}/>
        </Routes>
        </BrowserRouter>
    )
}
export default Router;
