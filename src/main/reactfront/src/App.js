import './App.css';
import {
    BrowserRouter, Routes, Router, Route,
} from "react-router-dom"
import Sign from './user/Sign'
import User from './user/User'
import Login from './user/Login'
import Main from "./Main";
import React from 'react';
import Navi from "./common/Navi";

function App() {
    return (
        <div>
            <BrowserRouter>
                <Navi/>
                <Routes>
                    <Route path="/" element={<Main/>}></Route>
                    <Route path="/login" element={<Login/>}></Route>
                    <Route path="/sign" element={<Sign/>}></Route>
                    <Route path="/user" element ={<User/>}></Route>
                    {/* 상단부터 라우트를 체크하면서 내려가기 때문에 마지막에 놓으면 예외처리를 손쉽게 할 수 있다. */}
                    {/*<Route path="*" element ={<Main/>} />*/}
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App;
