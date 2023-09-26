import './Sign.css';
import { useState } from "react";
import React from "react";

const Sign = () => {

    const [form, setForm] = useState({
        name : "",
        email : "",
        password : "",
    })

    /*
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    */

    const [error, setError] = useState({
        name : "",
        email : "",
        password : "",
    })

    const handleChange = e => {
        setForm({
            ...form,
            [e.target.name]: e.target.value,
        })
    }
    const validate = () => {
        const error = {
            name: "",
            email: "",
            password: "",
        }

        if (!form.name.trim()) {
            error.name = "이름을 입력하세요"
        }

        if(!form.email.trim().match(/^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/)) {
            error.email = "이메일을 올바르게 입력해 주세요"
        }

        if (!form.password.trim()) {
            error.password = "비밀번호를 입력하세요"
        }

        return error
    }

    const handleSubmit = e => {
        e.preventDefault();
        const error = validate()
        setError(error)

        if(Object.values(error).some(v => v)) {
            return
        }
        alert(JSON.stringify(form, null, 3))
    }


    return (
        <>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="name"
                    value={form.name}
                    placeholder="이름"
                    onChange={handleChange}
                />
                {/* 이메일 오류메시지를 출력한다 */}
                {error.name && <span>{error.name}</span>}
                <input
                    type="text"
                    name="email"
                    value={form.email}
                    placeholder="이메일"
                    onChange={handleChange}
                />
                {/* 이메일 오류메시지를 출력한다 */}
                {error.email && <span>{error.email}</span>}
                <input
                    type="password"
                    name="password"
                    value={form.password}
                    placeholder="비밀번호"
                    onChange={handleChange}
                />
                {/* 비밀번호 오류메시지를 출력한다 */}
                {error.password && <span>{error.password}</span>}
                <button type="submit">회원가입</button>
            </form>
        </>
    )
}

export default Sign;