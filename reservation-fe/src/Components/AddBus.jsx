import React, { useState } from "react";
import '../Styles/AddBus.css'
import axios from "axios";

export default function AddBus(){
    let [name,setname] = useState("")
    let [date_of_departure,setdate] = useState("")
    let [bus_number,setbus_number] = useState("")
    let [from_location,setfrom] = useState("")
    let [to_location,setto] = useState("")
    let [number_of_seats,setseats] = useState("")

    let busData = (
        name,date_of_departure,bus_number,from_location,to_location,number_of_seats
    )

    function addBusData(e){
        e.preventDefault()
        axios.post('http://localhost:8080/api/BUS',busData)
        .then((res)=>{
            console.log(res);
            alert("Bus Details Have Been Added Successfully")
        })
        .catch((err)=>{
            console.log(err);
            alert("Invalid Data Format")
        })

    }
    return(
        <div className="AddBus">
            <form onSubmit={addBusData} action="">
                <label htmlFor="">Bus Name:</label><input type="text" placeholder='Enter the bus name' required value={name} onChange={(e)=>setname(e.target.value)} />
                <label htmlFor="">Date Of Departure:</label><input type="date" placeholder='Enter the bus bate' required value={date_of_departure} onChange={(e)=>setdate(e.target.value)} />
                <label htmlFor="">Bus Number:</label><input type="text" placeholder='Enter the bus number' required value={bus_number} onChange={(e)=>setbus_number(e.target.value)} />
                <label htmlFor="">From:</label><input type="text" placeholder='Enter the From details' required value={from_location} onChange={(e)=>setfrom(e.target.value)} />
                <label htmlFor="">To:</label><input type="text" placeholder='Enter the To details' required value={to_location} onChange={(e)=>setto(e.target.value)} />
                <label htmlFor="">Number Of Seats:</label><input type="number" placeholder='Enter the seat details' required value={number_of_seats} onChange={(e)=>setseats(e.target.value)} />
                <button className="btn btn-danger">Add Bus</button>

            </form>
        </div>
    )
}