import './App.css';
import React from 'react';
import {Link} from "react-router-dom";

const Main = () => {
    return (
        <>
            <h3>메인 페이지입니다</h3>
            <Link to="/sign">회원가입</Link>
            <Link to="/login">로그인</Link>
        </>
    )
}

export default Main;
