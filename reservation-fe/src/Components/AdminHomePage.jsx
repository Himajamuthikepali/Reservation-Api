import React from "react";
import { Routes,Route } from "react-router-dom";
import AdminNavbar from './AdminNavbar'
import AdminDadhBoard from './AdminDashBoard'
import AddBus from "./AddBus";

export default function AdminHomePage(){
    return(
        <div>
           <AdminNavbar/>
           <Routes>
            <Route path='/' element={<AdminDadhBoard/>}/>
            <Route path="/addbus" element={<AddBus/>}/>
           </Routes>
        </div>
    )
}