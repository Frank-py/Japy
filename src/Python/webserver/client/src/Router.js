import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from "./pages/Login"
import NotFound from "./pages/NotFound"
const Router = () => {
    return (
        <BrowserRouter>
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="*" element={<NotFound/>}/>
        </Routes>
        </BrowserRouter>
    )
}
export default Router;