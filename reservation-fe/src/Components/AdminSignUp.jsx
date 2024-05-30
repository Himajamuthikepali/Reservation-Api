import React, { useState } from "react"
import '../Styles/AdminSignUp.css'
import axios from "axios"

export default function AdminSignUp(){
    let [name, setname] = useState("")
    let [phone,setphone] = useState("")
    let [email,setemail] = useState("")
    let [gst_number,setgast_number] = useState("")
    let [travels_name,settravels_name] = useState("")
    let [password,setpassword] = useState("")

    let data = {
        name,phone,email,gst_number,travels_name,password
    }

    function createAdmin(e){
        e.preventDefault()
        axios.post('http://localhost:8080/api/admins',data)
        .then((res)=>{
            alert("Data Added Successfully")
            console.log(res);
        })
        .catch((err)=>{
            alert("Invalid Data")
            console.log(err);
        })
    }

    return(
        <div className="AdminSignUp">
           <form onSubmit={createAdmin}  action="">
            <label htmlFor="">Name</label><input type="text" required placeholder='Enter the name' value={name} onChange={(e)=>setname(e.target.value)}/>
            <label htmlFor="">Email</label><input type="text" required placeholder='Enter the Email' value={email} onChange={(e)=>setemail(e.target.value)} />
            <label htmlFor="">Phone</label><input type="text" required placeholder='Enter the phone' value={phone} onChange={(e)=>setphone(e.target.value)}/>
            <label htmlFor="">gst_number</label><input type="text" required placeholder='Enter the gst_number' value={gst_number} onChange={(e)=>setgast_number(e.target.value)} />
            <label htmlFor="">Travels_name</label><input type="text" required placeholder='Enter the travels_name' value={travels_name} onChange={(e)=>settravels_name(e.target.value)} />
            <label htmlFor="">Password</label><input type="text" required placeholder='Enter the password' value={password} onChange={(e)=>setpassword(e.target.value)} />
            <button className='btn btn-danger'>Regiter</button>
           </form>
        </div>
    )
}