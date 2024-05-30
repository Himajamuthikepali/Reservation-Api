import React from "react";
import '../Styles/UserLogin.css'

export default function UserLogin() {
    return (
        <div className='UserLogin'>
            <form action="">
                <label htmlFor="">
                    UserName
                </label>
                <input type="text" placeholder='Enter The UserName' required />

                <label htmlFor="">
                    Password
                </label>
                <input type="text" placeholder='Enter The Password' required />
                <button className='btn btn-warning'>Login</button>
            </form>
        </div>
    )
}