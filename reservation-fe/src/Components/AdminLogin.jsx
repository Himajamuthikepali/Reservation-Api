import React, { useState } from "react";
import '../Styles/AdminLogin.css'
import { Link } from "react-router-dom";
import axios from "axios";

export default function AdminLogin() {
    let [email,setemail] = useState("")
    let [password,setpassword] = useState("")

    function verify(e){
        e.preventDefault()
        axios.post(`http://localhost:8080/api/admins/verify-by-email?email=$(email)&password=$(password)`)
        .then((res)=>{
            alert("Login Successfull")
        })
        .catch((err)=>{
            alert("Login Failed")
        })
    }
    return(
        <div className='AdminLogin'>
            <form onSubmit={verify} action="">
                <label htmlFor="">
                    Email
                </label>
                <input type="text" value={email} onChange={(e)=>{setemail(e.target.value)}} placeholder='Enter The Email' required />

                <label htmlFor="">
                    Password
                </label>
                <input type="text" value={password} onChange={(e)=>{setpassword(e.target.value)}} placeholder='Enter The Password' required />
                <button className='btn btn-primary'>Login</button>
            </form>
            <p>Are You The New User ? <Link to="/adminsignup">Register here..</Link></p>
        </div>
    )
}