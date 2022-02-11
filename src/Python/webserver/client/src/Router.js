import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Login from "./pages/Login"
import NotFound from "./pages/NotFound"
import Messenger from "./pages/Messenger"
const Router = () => {
    return (
        <BrowserRouter>
        <Routes>
            <Route exact path="/login" element={<Login/>}/>
            <Route exact path="/messenger/*" element={<Messenger/>} />
            <Route path="*" element={<NotFound/>}/>
            <Route path="/" element={<Navigate to="/login"/>}/>
        </Routes>
        </BrowserRouter>
    )
}
export default Router;